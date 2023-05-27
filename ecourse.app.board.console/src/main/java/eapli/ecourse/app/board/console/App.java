package eapli.ecourse.app.board.console;

import eapli.ecourse.app.common.console.ECourseBaseApplication;
import eapli.ecourse.app.common.console.presentation.authz.LoginAction;
import eapli.ecourse.infrastructure.auth.PasswordEncoderContext;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.ecourse.app.board.common.TcpClient;
import eapli.ecourse.app.board.console.presentation.MainMenu;

/**
 * eCourse Student Application.
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class App extends ECourseBaseApplication {
  /**
   * Empty constructor is private to avoid instantiation of this class.
   */
  private App() {}

  public static void main(final String[] args) {
    TcpClient tcpClient = new TcpClient("localhost", 9999);
    tcpClient.connect();

    new App().run(args);
  }

  @Override
  protected void doMain(final String[] args) {
    // if (new LoginAction().execute()) {
    final MainMenu menu = new MainMenu();
    menu.mainLoop();
    // }
  }

  @Override
  protected String appTitle() {
    return "Shared Board App";
  }

  @Override
  protected String appGoodbye() {
    return "Thank you for using 'Shared Board App'";
  }

  @Override
  protected void configureAuthz() {
    AuthzRegistry.configure(PersistenceContext.repositories().users(), new ClientPasswordPolicy(),
        PasswordEncoderContext.passwordHash());
  }

  // @SuppressWarnings("unchecked")
  @Override
  protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
    // typically there wouldn't be this watch dog here as this would be handled by a
    // backend server, but since we are for now dealing only with console
    // application clients we will do it here
    // dispatcher.subscribe(...);
  }
}
