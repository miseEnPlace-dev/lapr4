package eapli.ecourse.infrastructure.bootstrapers;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.usermanagement.application.AddUserController;
import eapli.ecourse.usermanagement.application.ListUsersController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 *
 * @author Paulo Gandra de Sousa
 *
 */
public class UsersBootstrapperBase {
  private static final Logger LOGGER = LogManager.getLogger(UsersBootstrapperBase.class);

  private final AddUserController userController = new AddUserController();
  private final ListUsersController listUserController = new ListUsersController();

  public UsersBootstrapperBase() {
    super();
  }

  /**
   * @param username
   * @param password
   * @param firstName
   * @param lastName
   * @param email
   * @param roles
   */
  protected SystemUser registerUser(final String username, final String password,
      final String firstName, final String lastName, final String email, final Set<Role> roles) {
    SystemUser u = null;
    try {
      u = userController.addUser(username, password, firstName, lastName, email, roles);
      LOGGER.debug("»»» {}", username);
    } catch (final IntegrityViolationException | ConcurrencyException e) {
      // assuming it is just a primary key violation due to the tentative
      // of inserting a duplicated user. let's just lookup that user
      u = listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
    }
    return u;
  }
}
