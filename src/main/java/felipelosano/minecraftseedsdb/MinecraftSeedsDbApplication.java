package felipelosano.minecraftseedsdb;

import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinecraftSeedsDbApplication {
  private final UserService userService;

  public MinecraftSeedsDbApplication(UserService userService) {
    this.userService = userService;
    userService.saveUser(new User("felipe", "losano", "felipelosano@outlook.com", "12345"));
  }


  public static void main(String[] args) {
    SpringApplication.run(MinecraftSeedsDbApplication.class, args);
  }
}
