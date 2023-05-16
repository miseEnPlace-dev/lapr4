package eapli.ecourse.usermanagement.application;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 *
 * @author losa
 */
@UseCaseController
public class ListUsersController {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final UserManagementService userSvc = AuthzRegistry.userService();

  public Iterable<SystemUser> allUsers() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    return userSvc.allUsers();
  }

  public Iterable<SystemUser> allManagers() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Iterable<SystemUser> users = userSvc.allUsers();
    Set<SystemUser> managers = new HashSet<>();

    for (SystemUser u : users)
      if (u.hasAny(ClientRoles.MANAGER))
        managers.add(u);

    return managers;
  }

  public Optional<SystemUser> find(final Username u) {
    return userSvc.userOfIdentity(u);
  }
}
