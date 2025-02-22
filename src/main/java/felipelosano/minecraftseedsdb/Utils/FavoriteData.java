package felipelosano.minecraftseedsdb.Utils;

public class FavoriteData {
  private Long userId;
  private String seedNumber;

  public FavoriteData(Long userId, String seedNumber) {
    this.userId = userId;
    this.seedNumber = seedNumber;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getSeedNumber() {
    return seedNumber;
  }

  public void setSeedNumber(String seedId) {
    this.seedNumber = seedId;
  }
}
