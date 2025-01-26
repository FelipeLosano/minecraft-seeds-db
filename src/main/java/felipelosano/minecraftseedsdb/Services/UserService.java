package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.DTO.User.UserRequestDTO;
import felipelosano.minecraftseedsdb.DTO.User.UserResponseDTO;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Repositories.SeedRepository;
import felipelosano.minecraftseedsdb.Repositories.UserRepository;
import felipelosano.minecraftseedsdb.Security.Enums.UserRole;
import felipelosano.minecraftseedsdb.Security.Services.PasswordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  private final SeedRepository seedRepository;
  UserRepository userRepository;
  PasswordService passwordService;

  public UserService(PasswordService passwordService, UserRepository userRepository, SeedRepository seedRepository) {
    this.userRepository = userRepository;
    this.passwordService = passwordService;
    this.seedRepository = seedRepository;
  }


  public List<UserResponseDTO> findAll() {
    List<User> users = userRepository.findAll();
    List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
    for (User user : users) {
      userResponseDTOs.add(new UserResponseDTO(user));
    }

    return userResponseDTOs;
  }

  public UserResponseDTO findById(Long id) {
    return new UserResponseDTO(userRepository.findById(id).orElse(null));
  }

  public UserResponseDTO saveUser(UserRequestDTO user) {
    User newUser = null;
    if (userRepository.findByEmail(user.email()) == null) {
      String hashedPassword = passwordService.hashPassword(user.password());
      newUser = new User(user.firstName(), user.lastName(), user.email(), hashedPassword);
      newUser = userRepository.save(newUser);
      return new UserResponseDTO(newUser);
    }


    return null;
  }

  public UserResponseDTO changeUserRole(Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      if (user.getRole() == UserRole.ADMIN) {
        user.setRole(UserRole.USER);
        return new UserResponseDTO(userRepository.save(user));
      }
      user.setRole(UserRole.ADMIN);
      return new UserResponseDTO(userRepository.save(user));
    }
    return null;
  }

  public UserResponseDTO updateUser(Long id, UserRequestDTO newUser) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      if (!newUser.password().equals(user.getPassword())) {
        String hashedPassword = passwordService.hashPassword(newUser.password());
        user.setPassword(hashedPassword);
      }
      user.setFirstName(newUser.firstName());
      user.setLastName(newUser.lastName());
      user.setEmail(newUser.email());
      user = userRepository.save(user);
      return new UserResponseDTO(user);
    }
    return null;
  }

  public boolean deleteUser(Long id) {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public UserResponseDTO toggleEnabled(Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      user.enable(!user.isEnabled());
      user = userRepository.save(user);
      return new UserResponseDTO(user);
    }
    return null;
  }

  public UserResponseDTO favoriteSeeds(Long userId, String seedNumber) {
    User user = userRepository.findById(userId).orElse(null);
    Seed seed = seedRepository.findBySeedNumber(seedNumber).orElse(null);
    if (user != null && seed != null) {
      if (user.getFavoritesIds().contains(seed.getId())) {
        return null;
      }
      List<Seed> newFavorites = user.getFavorites();
      newFavorites.add(seed);
      user.setFavorites(newFavorites);
      user = userRepository.save(user);
      return new UserResponseDTO(user);
    }
    return null;
  }

  public List<Long> getFavorites(Long userId) {
    User user = userRepository.findById(userId).orElse(null);
    if (user != null) {
      return user.getFavoritesIds();
    }
    return null;
  }
}
