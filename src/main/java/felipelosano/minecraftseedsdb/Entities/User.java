package felipelosano.minecraftseedsdb.Entities;

import felipelosano.minecraftseedsdb.Utils.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users_tb")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Roles role = Roles.USER;
  @NotBlank(message = "Field firstName must be filled")
  @Size(min = 3, message = "Field firstName must have min of 3 characters")
  private String firstName;
  @NotBlank(message = "Field lastName must be filled")
  @Size(min = 3, message = "Field lastName must have min of 3 characters")
  private String lastName;
  @NotBlank(message = "Field email must be filled")
  @Size(min = 3, message = "Field email must have min of 3 characters")
  @Email(message = "Invalid Email")
  private String email;
  @NotBlank(message = "Field password must be filled")
  @Size(min = 3, message = "Field password must have min of 3 characters")
  private String password;
  private String creationDate = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
  private String lastLogin;
  private Boolean enabled = true;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Seed> seeds = new ArrayList<>();

  public User() {
  }

  public User(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String dateOfPost) {
    this.creationDate = dateOfPost;
  }

  public String getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(String lastLogin) {
    this.lastLogin = lastLogin;
  }

  public List<Long> getSeedsIds() {
    return seeds.stream().map(Seed::getId).collect(Collectors.toList());
  }

  public Boolean isEnabled() {
    return enabled;
  }

  public void enable(Boolean enable) {
    enabled = enable;
  }
}
