package felipelosano.minecraftseedsdb.DTO.Seed;

import felipelosano.minecraftseedsdb.Entities.Image;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Utils.BiomesEnum;

import java.util.List;

public record SeedResponseDTO(Long id, String seedNumber, String version, String dateOfPost, List<Long> imageListIds,
                              Long userID, BiomesEnum biome, Boolean isVillage, List<Long> favoritedUsersIds) {

  public SeedResponseDTO(Seed seed) {
    this(seed.getId(), seed.getSeedNumber(), seed.getVersion(), seed.getDateOfPost(), seed.getImageListIds(), seed.getUser().getId(), seed.getBiome(), seed.getVillage(),seed.getFavoritedUsers());
  }
}
