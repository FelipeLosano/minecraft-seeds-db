package felipelosano.minecraftseedsdb.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images_tb")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Lob
  @Basic(fetch = FetchType.LAZY)
  private byte[] data;
}
