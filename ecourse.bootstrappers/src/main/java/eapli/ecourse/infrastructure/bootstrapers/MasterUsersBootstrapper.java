package eapli.ecourse.infrastructure.bootstrapers;

import java.util.HashSet;
import java.util.Set;

import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 */
public class MasterUsersBootstrapper extends UsersBootstrapperBase implements Action {

  @Override
  public boolean execute() {
    registerAdmin("admin", TestDataConstants.PASSWORD1, "Jane", "Doe Admin",
        "jane.doe@email.local");
    return true;
  }

  /**
   *
   */
  private void registerAdmin(final String username, final String password, final String firstName,
      final String lastName, final String email) {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.ADMIN);

    registerUser(username, password, firstName, lastName, email, roles);
  }
}
