package eapli.ecourse.clientusermanagement.application.eventhandlers;

import java.util.Optional;

import eapli.ecourse.clientusermanagement.domain.ClientUser;
import eapli.ecourse.clientusermanagement.domain.ClientUserBuilder;
import eapli.ecourse.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.ecourse.clientusermanagement.repositories.ClientUserRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.functional.Functions;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 *
 * @author Paulo Gandra de Sousa
 */
/* package */ class AddClientUserOnSignupAcceptedController {

  private final UserRepository repo = PersistenceContext.repositories().users();
  private final ClientUserRepository clientUserRepository =
      PersistenceContext.repositories().clientUsers();

  public ClientUser addClientUser(final NewUserRegisteredFromSignupEvent event) {
    final Optional<SystemUser> newUser = findUser(event);
    return newUser.map(u -> createClientUser(event, u)).orElseThrow(IllegalStateException::new);
  }

  private ClientUser createClientUser(final NewUserRegisteredFromSignupEvent event, SystemUser u) {
    final var clientUser = new ClientUserBuilder()
        .withMecanographicNumber(event.mecanographicNumber()).withSystemUser(u).build();
    return clientUserRepository.save(clientUser);
  }

  @SuppressWarnings("squid:S1488")
  private Optional<SystemUser> findUser(final NewUserRegisteredFromSignupEvent event) {
    // since we are using events, the actual user may not yet be
    // created, so lets give it a time and wait
    final Optional<SystemUser> newUser =
        Functions.retry(() -> repo.ofIdentity(event.username()), 500, 30);
    return newUser;
  }
}
