package eapli.ecourse.myclientuser.application;

import java.util.Optional;

import eapli.ecourse.clientusermanagement.domain.ClientUser;
import eapli.ecourse.clientusermanagement.repositories.ClientUserRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 *
 * @author Paulo Gandra de Sousa
 */
@ApplicationService
public class MyClientUserService {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final ClientUserRepository clientUsersRepo = PersistenceContext.repositories().clientUsers();

  public ClientUser me() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.CLIENT_USER);

    // TODO cache the client user object
    final Optional<ClientUser> me = clientUsersRepo.findByUsername(myUser().identity());

    return me.orElseThrow(IllegalStateException::new);
  }

  private SystemUser myUser() {
    return authz.session().map(UserSession::authenticatedUser)
        .orElseThrow(IllegalStateException::new);
  }
}
