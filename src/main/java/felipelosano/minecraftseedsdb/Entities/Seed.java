package felipelosano.minecraftseedsdb.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "seeds_tb")
public class Seed {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String seed;
  private String img;
  private String version;
  private Date dateOfPost;
  @ManyToOne
  private User user;

  public Seed() {
  }

  public Seed(String seed, String img, String version, Date dateOfPost, User user) {
    this.seed = seed;
    this.img = img;
    this.version = version;
    this.dateOfPost = dateOfPost;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public String getSeed() {
    return seed;
  }

  public void setSeed(String seed) {
    this.seed = seed;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Date getDateOfPost() {
    return dateOfPost;
  }

  public void setDateOfPost(Date dateOfPost) {
    this.dateOfPost = dateOfPost;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
