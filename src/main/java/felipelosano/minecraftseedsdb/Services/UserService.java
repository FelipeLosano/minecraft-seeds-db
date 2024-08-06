package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.Entities.User;
import felipelosano.minecraftseedsdb.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    if (repository.existsById(id)) {
      return repository.findById(id).get();
    }
    return null;
  }

  public User save(User user) {
    return repository.save(user);
  }
}
