package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Repositories.UserRepository;
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
    if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
      String hashedPassword = passwordService.hashPassword(user.getPassword());
      User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), hashedPassword);
      return userRepository.save(newUser);
    }
    return null;
  }

  public boolean login(String email, String password) {
    User user = userRepository.findByEmail(email).orElse(null);
    return user != null && passwordService.matches(password, user.getPassword());
  }

  public User updateUser(Long id, User newUser) {
    User user = findById(id);
    String hashedPassword = passwordService.hashPassword(newUser.getPassword());
    if (user != null) {
      user.setFirstName(newUser.getFirstName());
      user.setLastName(newUser.getLastName());
      user.setEmail(newUser.getEmail());
      user.setPassword(hashedPassword);


      return userRepository.save(user);
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
}
