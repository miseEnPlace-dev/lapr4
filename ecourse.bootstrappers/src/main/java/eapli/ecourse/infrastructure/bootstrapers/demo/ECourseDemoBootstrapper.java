package eapli.ecourse.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;

/**
 * eCourse Bootstrapping Demo data. This class bootstraps data for demo
 * purposes. It's main purpose
 * is to avoid entering data in the UI while testing and speedup the process.
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class ECourseDemoBootstrapper implements Action {

  private static final String POWERUSER_A1 = "poweruserA1";
  private static final String POWERUSER = "poweruser";

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();

  @Override
  public boolean execute() {
    // declare bootstrap actions
    final Action[] actions = { new BackofficeUsersBootstrapper(), new StudentBootstrapper(),
        new CoursesBootstrapper(), new QuestionsBootstrapper() };

    authenticateForBootstrapping();

    // execute all bootstrapping
    boolean ret = true;
    for (final Action boot : actions) {
      System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
      ret &= boot.execute();
    }
    return ret;
  }

  /**
   * authenticate a super user to be able to register new users
   *
   */
  protected void authenticateForBootstrapping() {
    authenticationService.authenticate(POWERUSER, POWERUSER_A1);
    Invariants.ensure(authz.hasSession());
  }

  private String nameOfEntity(final Action boot) {
    final String name = boot.getClass().getSimpleName();
    return Strings.left(name, name.length() - "Bootstrapper".length());
  }
}
