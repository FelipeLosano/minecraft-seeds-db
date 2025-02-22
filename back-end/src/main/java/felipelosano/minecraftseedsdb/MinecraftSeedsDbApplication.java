package felipelosano.minecraftseedsdb;

import felipelosano.minecraftseedsdb.Services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinecraftSeedsDbApplication {

  public MinecraftSeedsDbApplication(UserService userService) {}


  public static void main(String[] args) {
    SpringApplication.run(MinecraftSeedsDbApplication.class, args);
  }
}
