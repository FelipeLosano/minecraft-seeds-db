package felipelosano.minecraftseedsdb.Controllers;

import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getUser() {
    return userService.findAll();
  }

  @GetMapping(path = "{id}")
  public User getUserByID(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PostMapping
  public String createUser() {
    return "User created";
  }

  @PutMapping(path = "{id}")
  public String updateUser(@PathVariable int id) {
    return "User " + id + " updated";
  }

  @DeleteMapping(path = "{id}")
  public String deleteUser(@PathVariable int id) {
    return "User" + id + "deleted";
  }
}
