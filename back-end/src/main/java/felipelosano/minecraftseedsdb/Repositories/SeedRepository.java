package felipelosano.minecraftseedsdb.Repositories;

import felipelosano.minecraftseedsdb.Entities.Seed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeedRepository extends JpaRepository<Seed, Long> {
  Optional<Seed> findBySeedNumber(String seedNumber);

  Optional<List<Seed>> findByVersion(String version);
}
