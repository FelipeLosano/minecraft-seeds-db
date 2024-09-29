package felipelosano.minecraftseedsdb.Controllers;

import felipelosano.minecraftseedsdb.Entities.Image;
import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Services.ImageService;
import felipelosano.minecraftseedsdb.Services.SeedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("images")
public class ImageController {

  private final ImageService imageService;
  private final SeedService seedService;

  public ImageController(ImageService imageService, SeedService seedService) {
    this.imageService = imageService;
    this.seedService = seedService;
  }

  @GetMapping("{id}")
  public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
    byte[] imageData = imageService.getImage(id).getData();
    return ResponseEntity.ok()
            .header("Content-Type", "image/jpeg")
            .body(imageData);
    }

    @PostMapping
    public ResponseEntity<Object> createImage (@RequestParam("file") MultipartFile file, @RequestParam Long
    seedId, UriComponentsBuilder uriBuilder) throws IOException {
      Seed seed = seedService.findById(seedId);
      Image savedImage = imageService.saveImage(file, seed);
      URI uri = uriBuilder.path("images/{id}").buildAndExpand(savedImage.getId()).toUri();
      return ResponseEntity.created(uri).body(savedImage);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteImage (@PathVariable Long id){
      boolean checkDeletion = imageService.deleteImage(id);
      if (checkDeletion) {
        return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully");
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image not found");
    }
  }
