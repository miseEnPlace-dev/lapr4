package eapli.ecourse.app.backoffice.console;

import eapli.ecourse.app.backoffice.console.presentation.MainMenu;
import eapli.ecourse.app.common.console.ECourseBaseApplication;
import eapli.ecourse.app.common.console.presentation.authz.LoginUI;
import eapli.ecourse.clientusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.ecourse.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.ecourse.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.ecourse.infrastructure.authz.AuthenticationCredentialHandler;
import eapli.ecourse.infrastructure.authz.PasswordHashEncoder;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

/**
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class ECourseBackoffice extends ECourseBaseApplication {

  /**
   * avoid instantiation of this class.
   */
  private ECourseBackoffice() {
  }

  /**
   * @param args the command line arguments
   */
  public static void main(final String[] args) {

    new ECourseBackoffice().run(args);
  }

  @Override
  protected void doMain(final String[] args) {
    // login and go to main menu
    final boolean authenticated = new LoginUI(new AuthenticationCredentialHandler()).show();
    if (authenticated) {
      // go to main menu
      final MainMenu menu = new MainMenu();
      menu.mainLoop();
    }
  }

  @Override
  protected String appTitle() {
    return "eCourse Back Office";
  }

  @Override
  protected String appGoodbye() {
    return "eCourse Back Office";
  }

  @Override
  protected void configureAuthz() {
    AuthzRegistry.configure(PersistenceContext.repositories().users(), new ClientPasswordPolicy(),
        new PasswordHashEncoder());
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
    dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(),
        NewUserRegisteredFromSignupEvent.class);
    dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
  }
}
