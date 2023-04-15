package eapli.ecourse.clientusermanagement.application;

import eapli.ecourse.clientusermanagement.domain.ClientUser;
import eapli.ecourse.clientusermanagement.domain.MecanographicNumber;
import eapli.ecourse.clientusermanagement.repositories.ClientUserRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author mcn
 */
@ApplicationService
public class ClientUserService {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final ClientUserRepository repo = PersistenceContext.repositories().clientUsers();

  public Optional<ClientUser> findClientUserByMecNumber(final String mecNumber) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.ADMIN,
        ClientRoles.MANAGER);
    return repo.ofIdentity(MecanographicNumber.valueOf(mecNumber));
  }

  public Optional<ClientUser> findClientUserByUsername(final Username user) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.ADMIN);
    return repo.findByUsername(user);
  }
}

