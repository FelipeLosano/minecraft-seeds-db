package felipelosano.minecraftseedsdb.Repositories;

import felipelosano.minecraftseedsdb.Entities.Seed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeedRepository extends JpaRepository<Seed, Long> {
  Seed findBySeedNumber(String seedNumber);

  List<Seed> findByVersion(String version);
}
