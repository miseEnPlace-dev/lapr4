package eapli.ecourse.daemon.board.clientstate;

public class ClientState {
  private final CredentialStore credentialStore;

  private ClientState() {
    this.credentialStore = new CredentialStore();
  }

  public CredentialStore getCredentialStore() {
    return this.credentialStore;
  }

  // singleton thread-local instance: each thread will create its own instance of ClientState
  // therefore the clientState is thread safe
  private static final ThreadLocal<ClientState> _localStorage = new ThreadLocal<ClientState>() {
    protected ClientState initialValue() {
      return new ClientState();
    }
  };

  public static ClientState getInstance() {
    return _localStorage.get();
  }
}
