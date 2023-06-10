package eapli.ecourse.common.board.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class UserDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private String username;
  private String email;
  private String fullName;
  private Collection<Role> roles;

  public UserDTO(String username, String email, String fullName, Collection<Role> roles) {
    this.username = username;
    this.email = email;
    this.fullName = fullName;
    this.roles = roles;
  }

  public UserDTO(UserDTO userDTO) {
    this.username = userDTO.getUsername();
    this.email = userDTO.getEmail();
    this.fullName = userDTO.getFullName();
    this.roles = userDTO.getRoles();
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getFullName() {
    return fullName;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public static UserDTO from(SystemUser user) {
    return new UserDTO(user.username().toString(), user.email().toString(), user.name().toString(),
        new HashSet<>(user.roleTypes()));
  }

  @Override
  public String toString() {
    return "UserDTO [email=" + email + ", fullName=" + fullName + ", roles=" + roles + ", username="
        + username + "]";
  }
}
