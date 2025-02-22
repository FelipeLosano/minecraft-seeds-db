package felipelosano.minecraftseedsdb.DTO.Seed;

import felipelosano.minecraftseedsdb.Entities.Image;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Utils.BiomesEnum;

import java.util.List;

public record SeedRequestDTO(String seedNumber, List<Image> imageList, String version, Long userID, BiomesEnum biome, Boolean isVillage) {
  public SeedRequestDTO(Seed seed) {
    this(seed.getSeedNumber(), seed.getImageList(), seed.getVersion(), seed.getUserId(), seed.getBiome(), seed.getVillage());
  }
}
