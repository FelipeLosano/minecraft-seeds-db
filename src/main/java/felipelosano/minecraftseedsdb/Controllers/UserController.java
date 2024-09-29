package felipelosano.minecraftseedsdb.Controllers;

import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<User>> getUser() {
    List<User> users = userService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<Object> getUserByID(@PathVariable Long id) {
    User user = userService.findById(id);
    if (user != null) {
      return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
  }

  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
    User savedUser = userService.saveUser(user);
    URI uri = uriBuilder.path("users/{id}").buildAndExpand(savedUser.getId()).toUri();
    return ResponseEntity.created(uri).body(savedUser);
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody @Valid User newUser) {
    User updatedUser = userService.updateUser(id, newUser);
    if (updatedUser != null) {
      return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
    boolean checkDeletion = userService.deleteUser(id);
    if (checkDeletion) {
      return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
  }
}
