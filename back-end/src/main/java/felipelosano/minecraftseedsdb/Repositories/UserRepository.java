package felipelosano.minecraftseedsdb.Repositories;

import felipelosano.minecraftseedsdb.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
