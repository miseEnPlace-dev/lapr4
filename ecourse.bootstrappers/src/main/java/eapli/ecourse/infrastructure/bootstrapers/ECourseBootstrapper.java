package eapli.ecourse.infrastructure.bootstrapers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.ecourse.usermanagement.domain.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;

/**
 * eCourse Bootstrapping data. This class bootstraps Master/reference data of the application.
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class ECourseBootstrapper implements Action {
  private static final Logger LOGGER = LogManager.getLogger(ECourseBootstrapper.class);

  private static final String POWERUSER_PWD = "poweruserA1";
  private static final String POWERUSER = "poweruser";

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
  private final UserRepository userRepository = PersistenceContext.repositories().users();

  @Override
  public boolean execute() {
    // declare bootstrap actions
    final Action[] actions = {new MasterUsersBootstrapper()};

    registerPowerUser(userRepository);
    authenticateForBootstrapping();

    // execute all bootstrapping
    var ret = true;
    for (final Action boot : actions) {
      System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
      ret &= boot.execute();
    }
    return ret;
  }

  /**
   * Register a power user directly in the persistence layer as we need to circumvent authorizations
   * in the Application Layer.
   */
  public static boolean registerPowerUser(final UserRepository userRepository) {
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    userBuilder.withUsername(POWERUSER).withPassword(POWERUSER_PWD).withName("joe", "power")
        .withEmail("joe@email.org").withRoles(ClientRoles.POWER_USER);
    final SystemUser newUser = userBuilder.build();

    try {
      final SystemUser poweruser = userRepository.save(newUser);
      assert poweruser != null;
      return true;
    } catch (ConcurrencyException | IntegrityViolationException e) {
      // ignoring exception. assuming it is just a primary key violation
      // due to the tentative of inserting a duplicated user
      LOGGER.warn("Assuming {} already exists (activate trace log for details)",
          newUser.username());
      LOGGER.trace("Assuming existing record", e);
      return false;
    }
  }

  /**
   * authenticate a super user to be able to register new users
   *
   */
  protected void authenticateForBootstrapping() {
    authenticationService.authenticate(POWERUSER, POWERUSER_PWD);
    Invariants.ensure(authz.hasSession());
  }

  private String nameOfEntity(final Action boot) {
    final String name = boot.getClass().getSimpleName();
    return Strings.left(name, name.length() - "Bootstrapper".length());
  }
}
