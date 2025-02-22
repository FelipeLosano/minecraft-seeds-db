package felipelosano.minecraftseedsdb.DTO.Image;

import felipelosano.minecraftseedsdb.Entities.Image;

public record ImageResponseDTO(Long id) {

  public ImageResponseDTO(Image image) {
    this(image.getId());
  }
}
