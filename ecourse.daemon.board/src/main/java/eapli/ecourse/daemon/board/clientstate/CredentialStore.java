package eapli.ecourse.daemon.board.clientstate;

public class CredentialStore {
  private String username;
  private String password;
  private boolean isAuthenticated;

  public CredentialStore() {
    this.username = null;
    this.password = null;
    this.isAuthenticated = false;
  }

  public String getUsername() {
    return username;
  }

  public boolean isAuthenticated() {
    return this.isAuthenticated;
  }

  public final void storeCredentials(String username, String password) {
    this.username = username;
    this.password = password;
    this.isAuthenticated = true;
  };
}
