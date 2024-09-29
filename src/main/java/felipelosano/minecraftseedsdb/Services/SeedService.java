package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Repositories.SeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedService {
  SeedRepository seedRepository;

  public SeedService(SeedRepository seedRepository) {
    this.seedRepository = seedRepository;
  }

  public List<Seed> findAll() {
    return seedRepository.findAll();
  }

  public Seed findById(Long id) {
    if (seedRepository.existsById(id)) {
      return seedRepository.findById(id).get();
    }
    return null;
  }

  public Seed findBySeedNumber(String seedNumber) {
    return seedRepository.findBySeedNumber(seedNumber).orElse(null);
  }

  public List<Seed> findByVersion(String version) {
    return seedRepository.findByVersion(version).orElse(null);
  }

  public Seed saveSeed(Seed seed) {
    if (seedRepository.findBySeedNumber(seed.getSeedNumber()).isEmpty()) {
      return seedRepository.save(seed);
    }
    return null;
  }

  public boolean deleteSeed(Long id) {
    if (seedRepository.existsById(id)) {
      seedRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
