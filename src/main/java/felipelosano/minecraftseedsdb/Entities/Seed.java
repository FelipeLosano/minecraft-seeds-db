package felipelosano.minecraftseedsdb.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
