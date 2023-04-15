package eapli.ecourse.app.other.console;

import eapli.ecourse.app.common.console.ECourseBaseApplication;
import eapli.ecourse.app.common.console.presentation.authz.LoginAction;
import eapli.ecourse.app.other.console.presentation.MainMenu;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BasePasswordPolicy;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

/**
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class OtherApp extends ECourseBaseApplication {

  /**
   * Empty constructor is private to avoid instantiation of this class.
   */
  private OtherApp() {}

  public static void main(final String[] args) {
    System.out.println();

    AuthzRegistry.configure(PersistenceContext.repositories().users(), new ClientPasswordPolicy(),
        new PlainTextEncoder());

    new OtherApp().run(args);
  }

  @Override
  protected void doMain(String[] args) {
    // login and go to main menu
    // if (new LoginAction(ClientRoles.MANAGER).execute()) {
    // final MainMenu menu = new MainMenu();
    // menu.mainLoop();
    // }
  }

  @Override
  protected String appTitle() {
    return "Base POS";
  }

  @Override
  protected String appGoodbye() {
    return "Signing out";
  }

  @Override
  protected void doSetupEventHandlers(EventDispatcher dispatcher) {
    // TODO setup event handlers for your app
  }
}
