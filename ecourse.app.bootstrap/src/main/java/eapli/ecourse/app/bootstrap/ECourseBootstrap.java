package eapli.ecourse.app.bootstrap;

import eapli.ecourse.app.common.console.ECourseBaseApplication;
import eapli.ecourse.clientusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.ecourse.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.ecourse.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.ecourse.infrastructure.bootstrapers.ECourseBootstrapper;
import eapli.ecourse.infrastructure.bootstrapers.demo.ECourseDemoBootstrapper;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.infrastructure.smoketests.ECourseDemoSmokeTester;
import eapli.ecourse.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.io.util.Console;
import eapli.framework.util.ArrayPredicates;

/**
 * eCourse Bootstrapping data app
 */
@SuppressWarnings("squid:S106")
public final class ECourseBootstrap extends ECourseBaseApplication {

  private boolean isToBootstrapDemoData;
  private boolean isToRunSampleE2E;
  private boolean isToWaitInTheEnd;

  /**
   * avoid instantiation of this class.
   */
  private ECourseBootstrap() {}

  public static void main(final String[] args) {

    new ECourseBootstrap().run(args);
  }

  @Override
  protected void doMain(final String[] args) {
    handleArgs(args);

    System.out.println("\n\n------- MASTER DATA -------");
    new ECourseBootstrapper().execute();

    if (isToBootstrapDemoData) {
      System.out.println("\n\n------- DEMO DATA -------");
      new ECourseDemoBootstrapper().execute();
    }
    if (isToRunSampleE2E) {
      System.out.println("\n\n------- BASIC SCENARIO -------");
      new ECourseDemoSmokeTester().execute();
    }

    if (isToWaitInTheEnd) {
      Console.readLine("\n\n>>>>>> Enter to finish the program.");
    }
  }

  private void handleArgs(final String[] args) {
    isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:basic");
    if (isToRunSampleE2E) {
      isToBootstrapDemoData = true;
    } else {
      isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
    }

    isToWaitInTheEnd = ArrayPredicates.contains(args, "-wait");
  }

  @Override
  protected String appTitle() {
    return "Bootstrapping eCourse data ";
  }

  @Override
  protected String appGoodbye() {
    return "Bootstrap data done.";
  }

  @Override
  protected void configureAuthz() {
    AuthzRegistry.configure(PersistenceContext.repositories().users(), new ClientPasswordPolicy(),
        new PlainTextEncoder());
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
    dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(),
        NewUserRegisteredFromSignupEvent.class);
    dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
  }
}
