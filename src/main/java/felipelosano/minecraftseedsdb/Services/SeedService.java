package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Repositories.SeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedService {
  SeedRepository repository;

  public SeedService(SeedRepository repository) {
    this.repository = repository;
  }

  public List<Seed> findAll() {
    return repository.findAll();
  }

  public Seed findById(Long id) {
    if (repository.existsById(id)) {
      return repository.findById(id).get();
    }
    return null;
  }

  public Seed findBySeedNumber(String seedNumber) {
    if (repository.findBySeedNumber(seedNumber) != null) {
      return repository.findBySeedNumber(seedNumber);
    }
    return null;
  }

  public List<Seed> findByVersion(String version) {
    if (repository.findByVersion(version) != null) {
      return repository.findByVersion(version);
    }
    return null;
  }

  public Seed saveSeed(Seed seed) {
    Seed newSeed = new Seed(seed.getSeedNumber(), seed.getImageList(), seed.getVersion(), seed.getUser());
    if (repository.findBySeedNumber(newSeed.getSeedNumber()) == null) {
      return repository.save(newSeed);
    }
    return null;
  }

  public Seed updateSeed(Long id, Seed newSeed) {
    Seed seed = findById(id);
    if (seed != null) {
      seed.setImageList(newSeed.getImageList());
      seed.setDateOfPost(newSeed.getDateOfPost());

      return repository.save(seed);
    }
    return null;
  }

  public boolean deleteSeed(Long id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }
}
