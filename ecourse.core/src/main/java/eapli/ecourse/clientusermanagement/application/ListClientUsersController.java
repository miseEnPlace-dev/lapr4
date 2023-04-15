package eapli.ecourse.clientusermanagement.application;

import eapli.ecourse.clientusermanagement.domain.ClientUser;
import eapli.ecourse.clientusermanagement.repositories.ClientserRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author losa
 */
public class ListClientUsersController {
  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  private final ClientUserRepository repo = PersistenceContext.repositories().clientUsers();

  public Iterable<ClientUser> activeClientUsers() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.ADMIN);

    return this.repo.findAllActive();
  }
}
