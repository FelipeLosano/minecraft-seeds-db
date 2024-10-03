package felipelosano.minecraftseedsdb.Controllers;

import felipelosano.minecraftseedsdb.DTO.AuthDTO;
import felipelosano.minecraftseedsdb.DTO.LoginResponseDTO;
import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Security.Enums.UserRole;
import felipelosano.minecraftseedsdb.Security.Services.TokenService;
import felipelosano.minecraftseedsdb.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

  private final UserService userService;

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @GetMapping
  public ResponseEntity<List<User>> getUser() {
    List<User> users = userService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Object> getUserByID(@PathVariable Long id) {
    User user = userService.findById(id);
    if (user != null) {
      return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((User) auth.getPrincipal());

    User user = ((User) auth.getPrincipal());
    user.setLastLogin(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

    userService.updateUser(((User) auth.getPrincipal()).getId(), user);

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  @PostMapping(path = "/register")
  public ResponseEntity<Object> createUser(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
    User savedUser = userService.saveUser(user);
    if (savedUser != null) {
      URI uri = uriBuilder.path("users/{id}").buildAndExpand(savedUser.getId()).toUri();
      return ResponseEntity.created(uri).body(savedUser);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody @Valid User newUser) {
    User updatedUser = userService.updateUser(id, newUser);
    if (updatedUser != null) {
      return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
  }

  @PutMapping(path = "/{id}/enable")
  public ResponseEntity<Object> toggleEnable(@PathVariable Long id) {
    User user = userService.toggleEnabled(id);
    if (user != null) {
      return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
  }

  @PutMapping(path = "/{id}/role")
  public ResponseEntity<Object> changeUserRole(@PathVariable Long id) {
    User user = userService.changeUserRole(id);

    if (user != null) {
      return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
    boolean checkDeletion = userService.deleteUser(id);
    if (checkDeletion) {
      return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
  }
}
