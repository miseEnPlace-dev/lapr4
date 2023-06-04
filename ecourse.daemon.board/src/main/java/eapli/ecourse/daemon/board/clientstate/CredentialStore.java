package eapli.ecourse.daemon.board.clientstate;

import eapli.ecourse.common.board.dto.UserDTO;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CredentialStore {
  private UserDTO user;
  private boolean isAuthenticated;

  public CredentialStore() {
    this.user = null;
    this.isAuthenticated = false;
  }

  public UserDTO getUser() {
    return this.user;
  }

  public boolean isAuthenticated() {
    return this.isAuthenticated;
  }

  public final void store(SystemUser systemUser) {
    this.user = UserDTO.from(systemUser);
    this.isAuthenticated = true;
  }

  public final void store(UserDTO userDTO) {
    this.user = new UserDTO(userDTO);
    this.isAuthenticated = true;
  }

  public final void clear() {
    this.user = null;
    this.isAuthenticated = false;
  }
}
