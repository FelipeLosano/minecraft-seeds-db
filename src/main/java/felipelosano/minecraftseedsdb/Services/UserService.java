package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class UserService {

  UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    if (repository.existsById(id)) {
      return repository.findById(id).get();
    }
    return null;
  }

  public User saveUser(User user) {
    User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    if (repository.findByEmail(newUser.getEmail()) == null) {
      return repository.save(newUser);
    }
    return null;
  }

  public User updateUser(Long id, User newUser) {
    User user = findById(id);
    if (user != null) {
      user.setFirstName(newUser.getFirstName());
      user.setLastName(newUser.getLastName());
      user.setEmail(newUser.getEmail());
      user.setPassword(newUser.getPassword());


      return repository.save(user);
    }
    return null;
  }

  public boolean deleteUser(Long id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }
}
