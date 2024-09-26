package felipelosano.minecraftseedsdb.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_tb")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
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
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  private List<Seed> seeds;

  public User(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public User() {
  }

  public Long getId() {
    return id;
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
}
