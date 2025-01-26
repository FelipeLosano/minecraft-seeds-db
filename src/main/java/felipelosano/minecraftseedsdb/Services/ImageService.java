package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.DTO.Image.ImageResponseDTO;
import felipelosano.minecraftseedsdb.DTO.Seed.SeedResponseDTO;
import felipelosano.minecraftseedsdb.Entities.Image;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Repositories.ImageRepository;
import felipelosano.minecraftseedsdb.Repositories.UserRepository;
import felipelosano.minecraftseedsdb.Utils.ImageChecker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
  private final UserRepository userRepository;
  ImageRepository imageRepository;
  ImageChecker imageChecker = new ImageChecker();

  public ImageService(ImageRepository imageRepository, UserRepository userRepository) {
    this.imageRepository = imageRepository;
    this.userRepository = userRepository;
  }

  public ImageResponseDTO saveImage(MultipartFile file, SeedResponseDTO seedDTO) throws IOException {
    if (imageChecker.isImage(file)) {
      Seed seed = new Seed(seedDTO.seedNumber(), seedDTO.imageList(), seedDTO.version(), userRepository.findById(seedDTO.userID()).orElse(null), seedDTO.biome(), seedDTO.isVillage());
      Image image = new Image(file.getBytes(), seed);
      if (seed.getUser() != null && !file.isEmpty()) {
        return new ImageResponseDTO(imageRepository.save(image));
      }
    }
    return null;
  }

  public boolean deleteImage(Long id) {
    if (imageRepository.existsById(id)) {
      imageRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public Image getImage(Long id) {
    return imageRepository.findById(id).orElse(null);
  }
}
