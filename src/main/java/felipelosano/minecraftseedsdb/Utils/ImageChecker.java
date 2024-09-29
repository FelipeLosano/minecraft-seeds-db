package felipelosano.minecraftseedsdb.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ImageChecker {

  private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "bmp", "webp");

  // Method to check if file is an image by MIME type
  public boolean isImageByMimeType(MultipartFile file) {
    String contentType = file.getContentType();
    return contentType != null && contentType.startsWith("image/");
  }

  // Method to get file extension
  public Optional<String> getFileExtension(MultipartFile file) {
    String originalFilename = file.getOriginalFilename();
    if (originalFilename != null && originalFilename.contains(".")) {
      return Optional.of(originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase());
    }
    return Optional.empty();
  }

  // Method to check if the file extension belongs to an image
  public boolean isImageByExtension(MultipartFile file) {
    Optional<String> extension = getFileExtension(file);
    return extension.isPresent() && IMAGE_EXTENSIONS.contains(extension.get());
  }

  // Combined method: MIME type and extension check
  public boolean isImage(MultipartFile file) {
    return isImageByMimeType(file) && isImageByExtension(file);
  }
}
