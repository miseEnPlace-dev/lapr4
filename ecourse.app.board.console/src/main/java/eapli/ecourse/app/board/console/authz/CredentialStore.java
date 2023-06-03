package eapli.ecourse.app.board.console.authz;

import eapli.ecourse.infrastructure.authz.CredentialHandler;

public class CredentialStore {
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public final CredentialHandler STORE_CREDENTIALS = (u, p, r) -> {
    this.username = u;
    this.password = p;
    return true;
  };
}
