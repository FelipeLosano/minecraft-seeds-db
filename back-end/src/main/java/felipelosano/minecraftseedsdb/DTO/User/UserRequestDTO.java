package felipelosano.minecraftseedsdb.DTO.User;

import felipelosano.minecraftseedsdb.Entities.User;

public record UserRequestDTO(String firstName, String lastName, String email, String password, String lastLogin) {
  public UserRequestDTO(User user) {
    this(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getLastLogin());
  }
}
