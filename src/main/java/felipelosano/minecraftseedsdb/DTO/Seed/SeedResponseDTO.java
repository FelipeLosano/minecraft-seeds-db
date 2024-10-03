package felipelosano.minecraftseedsdb.DTO.Seed;

import felipelosano.minecraftseedsdb.Entities.Image;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Utils.BiomesEnum;

import java.util.List;

public record SeedResponseDTO(Long id, String seedNumber, String version, String dateOfPost, List<Image> imageList,
                              Long userID, BiomesEnum biome) {

  public SeedResponseDTO(Seed seed) {
    this(seed.getId(), seed.getSeedNumber(), seed.getVersion(), seed.getDateOfPost(), seed.getImageList(), seed.getUser().getId(), seed.getBiome());
  }
}
