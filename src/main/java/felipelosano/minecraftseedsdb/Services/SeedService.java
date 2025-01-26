package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.DTO.Seed.SeedRequestDTO;
import felipelosano.minecraftseedsdb.DTO.Seed.SeedResponseDTO;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Repositories.SeedRepository;
import felipelosano.minecraftseedsdb.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeedService {
  private final UserRepository userRepository;
  SeedRepository seedRepository;

  public SeedService(SeedRepository seedRepository, UserRepository userRepository) {
    this.seedRepository = seedRepository;
    this.userRepository = userRepository;
  }

  public List<SeedResponseDTO> findAll() {
    List<SeedResponseDTO> seeds = new ArrayList<>();
    for (Seed seed : seedRepository.findAll()) {
      seeds.add(new SeedResponseDTO(seed));
    }
    return seeds;
  }

  public SeedResponseDTO findById(Long id) {
    if (seedRepository.existsById(id)) {
      return new SeedResponseDTO(seedRepository.findById(id).get());
    }
    return null;
  }

  public SeedResponseDTO findBySeedNumber(String seedNumber) {
    return new SeedResponseDTO(seedRepository.findBySeedNumber(seedNumber).orElse(null));
  }

  public List<SeedResponseDTO> findByVersion(String version) {
    List<SeedResponseDTO> seedsDTO = new ArrayList<>();
    List<Seed> seeds = seedRepository.findByVersion(version).orElse(null);
    if (seeds != null) {
      for (Seed seed : seeds) {
        seedsDTO.add(new SeedResponseDTO(seed));
      }
    }
    return seedsDTO;
  }

  public SeedResponseDTO saveSeed(SeedRequestDTO seedDTO) {
    if (seedRepository.findBySeedNumber(seedDTO.seedNumber()).isEmpty()) {
      Seed seed = new Seed(seedDTO.seedNumber(), seedDTO.imageList(), seedDTO.version(), userRepository.findById(seedDTO.userID()).orElse(null), seedDTO.biome(), seedDTO.isVillage());
      if (seed.getUser() != null) {
        return new SeedResponseDTO(seedRepository.save(seed));
      }
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
