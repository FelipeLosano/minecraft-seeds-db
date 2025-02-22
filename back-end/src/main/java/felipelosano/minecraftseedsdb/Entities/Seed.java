package felipelosano.minecraftseedsdb.Entities;

import felipelosano.minecraftseedsdb.Utils.BiomesEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  @NotNull(message = "Field images must be filled")
  private List<Image> images = new ArrayList<>();
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  @Enumerated(EnumType.STRING)
  @NotNull(message = "field biome must be filled")
  private BiomesEnum Biome;
  private Boolean isVillage;
  @ManyToMany(mappedBy = "favoriteSeeds")
  private List<User> favoritedUsers = new ArrayList<>();

  public Seed(String seedNumber, List<Image> images, String version, User user, BiomesEnum biome, Boolean isVillage) {
    this.seedNumber = seedNumber;
    this.images = images;
    this.version = version;
    this.user = user;
    this.Biome = biome;
    this.isVillage = isVillage;
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

  public List<Long> getImageListIds() {
    return images.stream().map(Image::getId).collect(Collectors.toList());
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

  public BiomesEnum getBiome() {
    return Biome;
  }

  public void setBiome(BiomesEnum biome) {
    Biome = biome;
  }

  public Boolean getVillage() {
    return isVillage;
  }

  public void setVillage(Boolean village) {
    isVillage = village;
  }

  public List<Long> getFavoritedUsers() {
    return favoritedUsers.stream().map(User::getId).collect(Collectors.toList());
  }
}
