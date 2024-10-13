package felipelosano.minecraftseedsdb;

import felipelosano.minecraftseedsdb.DTO.User.UserRequestDTO;
import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinecraftSeedsDbApplication {
  private final UserService userService;

  public MinecraftSeedsDbApplication(UserService userService) {
    this.userService = userService;
    UserRequestDTO user = new UserRequestDTO(new User("felipe", "losano", "felipelosano@outlook.com", "12345"));
    userService.saveUser(user);
    userService.changeUserRole(Long.parseLong("1"));
  }


  public static void main(String[] args) {
    SpringApplication.run(MinecraftSeedsDbApplication.class, args);
  }
}
