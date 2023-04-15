package eapli.ecourse.usermanagement.application;

import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 *
 * @author Fernando
 */
@UseCaseController
public class DeactivateUserController {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final UserManagementService userSvc = AuthzRegistry.userService();

  public Iterable<SystemUser> activeUsers() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.ADMIN);

    return userSvc.activeUsers();
  }

  public SystemUser deactivateUser(final SystemUser user) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.ADMIN);

    return userSvc.deactivateUser(user);
  }
}
