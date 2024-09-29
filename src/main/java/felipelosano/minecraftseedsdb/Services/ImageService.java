package felipelosano.minecraftseedsdb.Services;

import felipelosano.minecraftseedsdb.Entities.Image;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Repositories.ImageRepository;
import felipelosano.minecraftseedsdb.Utils.ImageChecker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
  ImageRepository imageRepository;
  ImageChecker imageChecker = new ImageChecker();

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public Image saveImage(MultipartFile file, Seed seed) throws IOException {
    if (imageChecker.isImage(file)) {
      Image image = new Image(file.getBytes(), seed);
      if (seed != null && !file.isEmpty()) {
        return imageRepository.save(image);
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
