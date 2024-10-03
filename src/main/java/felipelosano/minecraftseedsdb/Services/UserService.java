package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Repositories.UserRepository;
import felipelosano.minecraftseedsdb.Security.Enums.UserRole;
import felipelosano.minecraftseedsdb.Security.Services.PasswordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  UserRepository userRepository;
  PasswordService passwordService;

  public UserService(PasswordService passwordService, UserRepository userRepository) {
    this.userRepository = userRepository;
    this.passwordService = passwordService;
  }


  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public User saveUser(User user) {
    User newUser = null;
    if (userRepository.findByEmail(user.getEmail()) == null) {
      String hashedPassword = passwordService.hashPassword(user.getPassword());
      newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), hashedPassword);
      if (user.getRole() != null) {
        newUser.setRole(user.getRole());
      }
      newUser = userRepository.save(newUser);
    }


    return newUser;
  }

  public User changeUserRole(Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      if (user.getRole() == UserRole.ADMIN) {
        user.setRole(UserRole.USER);
        return userRepository.save(user);
      }
      user.setRole(UserRole.ADMIN);
      return userRepository.save(user);
    }
    return null;
  }

  public User updateUser(Long id, User newUser) {
    User user = findById(id);
    String hashedPassword = passwordService.hashPassword(newUser.getPassword());
    if (user != null) {
      user.setFirstName(newUser.getFirstName());
      user.setLastName(newUser.getLastName());
      user.setEmail(newUser.getEmail());
      user.setPassword(hashedPassword);
      user = userRepository.save(user);
    }
    return user;
  }

  public boolean deleteUser(Long id) {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public User toggleEnabled(Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      user.enable(!user.isEnabled());
      user = userRepository.save(user);
    }
    return user;
  }
}
