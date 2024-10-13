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

  @ManyToOne
  @JoinColumn(name = "seed_id", referencedColumnName = "id")
  private Seed seed;

  public Image() {
  }

  public Image(byte[] data, Seed seed) {
    this.data = data;
    this.seed = seed;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public Long getId() {
    return id;
  }

  public Long getSeedId() {
    return seed.getId();
  }

  public void setSeed(Seed seed) {
    this.seed = seed;
  }
}
