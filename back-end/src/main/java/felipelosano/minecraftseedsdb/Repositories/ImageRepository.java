package felipelosano.minecraftseedsdb.Repositories;

import felipelosano.minecraftseedsdb.Entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
