package isep.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String email;
  private String fullName;
  private String shortName;
  private String password;

  protected SystemUser() {
  }

  public SystemUser(String email, String fullName, String shortName, String password) {
    this.email = email;
    this.fullName = fullName;
    this.shortName = shortName;
    this.password = password;
  }
}
