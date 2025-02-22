package felipelosano.minecraftseedsdb.DTO.User;

import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Security.Enums.UserRole;

import java.util.List;

public record UserResponseDTO(Long id, String firstName, String lastName, String email, UserRole role,
                              String creationDate, String lastLogin, Boolean enabled, List<Long> seedsIds, List<Long> favoriteSeedsIds) {

  public UserResponseDTO(User user) {
    this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole(), user.getCreationDate(), user.getLastLogin(), user.isEnabled(), user.getSeedsIds(),user.getFavoritesIds());
  }
}
