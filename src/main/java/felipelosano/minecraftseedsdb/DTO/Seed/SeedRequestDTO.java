package felipelosano.minecraftseedsdb.DTO.Seed;

import felipelosano.minecraftseedsdb.Entities.Image;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Entities.User;

import java.util.List;

public record SeedRequestDTO(String seedNumber, List<Image> imageList, String version, Long userID) {
  public SeedRequestDTO(Seed seed) {
    this(seed.getSeedNumber(), seed.getImageList(), seed.getVersion(), seed.getUserId());
  }
}
