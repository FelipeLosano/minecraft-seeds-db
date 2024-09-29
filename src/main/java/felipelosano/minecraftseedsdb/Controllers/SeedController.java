package felipelosano.minecraftseedsdb.Controllers;

import felipelosano.minecraftseedsdb.Entities.Seed;
import felipelosano.minecraftseedsdb.Services.SeedService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("seeds")
public class SeedController {

  private final SeedService seedService;

  public SeedController(SeedService seedService) {
    this.seedService = seedService;
  }

  @GetMapping
  public ResponseEntity<List<Seed>> getSeeds() {
    List<Seed> seeds = seedService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(seeds);
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<Object> getSeedByID(@PathVariable Long id) {
    Seed seed = seedService.findById(id);
    if (seed != null) {
      return ResponseEntity.status(HttpStatus.OK).body(seed);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seed not found");
  }

  @GetMapping(path = "{version}")
  public ResponseEntity<List<Seed>> getSeedsByVersion(@PathVariable String version) {
    List<Seed> seeds = seedService.findByVersion(version);
    return ResponseEntity.status(HttpStatus.OK).body(seeds);
  }

  @GetMapping(path = "{seedNumber}")
  public ResponseEntity<Seed> getSeedsBySeedNumber(@PathVariable String seedNumber) {
    Seed seed = seedService.findBySeedNumber(seedNumber);
    return ResponseEntity.status(HttpStatus.OK).body(seed);
  }

  @PostMapping
  public ResponseEntity<Object> createSeed(@RequestBody @Valid Seed seed, UriComponentsBuilder uriBuilder) {
    Seed savedSeed = seedService.saveSeed(seed);
    URI uri = uriBuilder.path("seeds/{id}").buildAndExpand(savedSeed.getId()).toUri();
    return ResponseEntity.created(uri).body(savedSeed);
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<Object> deleteSeed(@PathVariable Long id) {
    boolean checkDeletion = seedService.deleteSeed(id);
    if (checkDeletion) {
      return ResponseEntity.status(HttpStatus.OK).body("Seed deleted successfully");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Seed not found");
  }
}
