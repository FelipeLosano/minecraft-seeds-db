package felipelosano.minecraftseedsdb.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seeds_tb")
public class Seed {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotBlank(message = "Field seedNumber must be filled")
  @Size(min = 3, message = "Field seedNumber must have min of 3 characters")
  @Pattern(regexp = "^-?\\d*$")
  private String seedNumber;
  @NotBlank(message = "Field version must be filled")
  @Size(min = 3, message = "Field version must have min of 3 characters")
  private String version;
  private String dateOfPost = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
  @OneToMany(mappedBy = "seed", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
//  @NotNull(message = "Field images must be filled")
  private List<Image> images = new ArrayList<>();
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public Seed(String seedNumber, List<Image> images, String version, User user) {
    this.seedNumber = seedNumber;
    this.images = images;
    this.version = version;
    this.user = user;
  }

  public Seed() {
  }

  public Long getId() {
    return id;
  }

  public String getSeedNumber() {
    return seedNumber;
  }

  public void setSeedNumber(String seedNumber) {
    this.seedNumber = seedNumber;
  }

  public List<Image> getImageList() {
    return images;
  }

  public void setImageList(List<Image> images) {
    this.images = images;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getDateOfPost() {
    return dateOfPost;
  }

  public void setDateOfPost(String dateOfPost) {
    this.dateOfPost = dateOfPost;
  }

  public Long getUserId() {
    return user.getId();
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
