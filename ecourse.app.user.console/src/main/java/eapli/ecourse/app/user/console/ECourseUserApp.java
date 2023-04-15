package eapli.ecourse.app.user.console;

import eapli.ecourse.app.common.console.ECourseBaseApplication;
import eapli.ecourse.app.user.console.presentation.FrontMenu;
import eapli.ecourse.clientusermanagement.application.eventhandlers.MealBookedWatchDog;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.mealbooking.domain.BookedEvent;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

/**
 * eCourse User Application.
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class ECourseUserApp extends ECourseBaseApplication {

  /**
   * Empty constructor is private to avoid instantiation of this class.
   */
  private ECourseUserApp() {}

  public static void main(final String[] args) {

    new ECourseUserApp().run(args);
  }

  @Override
  protected void doMain(final String[] args) {
    new FrontMenu().show();
  }

  @Override
  protected String appTitle() {
    return "my eCourse";
  }

  @Override
  protected String appGoodbye() {
    return "Thank you for using 'my eCourse'";
  }

  @Override
  protected void configureAuthz() {
    AuthzRegistry.configure(PersistenceContext.repositories().users(), new ClientPasswordPolicy(),
        new PlainTextEncoder());
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
    // typically there wouldn't be this watch dog here as this would be handled by a
    // backend server, but since we are for now dealing only with console
    // application clients we will do it here
    // dispatcher.subscribe(...);
  }
}
