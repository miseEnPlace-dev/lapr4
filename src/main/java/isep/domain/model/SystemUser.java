package isep.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idz;
  private String emailz;
  private String fullNamez;
  private String shortNamez;
  private String passwordz;

  protected SystemUser() {}

  public SystemUser(String email, String fullName, String shortName, String password) {
    this.emailz = email;
    this.fullNamez = fullName;
    this.shortNamez = shortName;
    this.passwordz = password;
  }
}
