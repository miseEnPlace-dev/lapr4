package eapli.ecourse.usermanagement.application;

import java.util.Calendar;
import java.util.Set;

import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

/**
 * Created by nuno on 21/03/16.
 */
@UseCaseController
public class AddUserController {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final UserManagementService userSvc = AuthzRegistry.userService();

  /**
   * Get existing RoleTypes available to the user.
   *
   * @return a list of RoleTypes
   */
  public Role[] getRoleTypes() {
    return ClientRoles.nonUserValues();
  }

  public SystemUser addUser(final String username, final String password, final String firstName,
      final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.ADMIN);

    return userSvc.registerNewUser(username, password, firstName, lastName, email, roles,
        createdOn);
  }

  public SystemUser addUser(final String username, final String password, final String firstName,
      final String lastName, final String email, final Set<Role> roles) {
    return addUser(username, password, firstName, lastName, email, roles,
        CurrentTimeCalendars.now());
  }
}
