package eapli.ecourse.usermanagement.application.eventhandlers;

import eapli.ecourse.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.ecourse.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.ecourse.usermanagement.domain.UserBuilderHelper;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.infrastructure.pubsub.PubSubRegistry;

/**
 * @author Paulo Gandra de Sousa
 */
@UseCaseController
/* package */ class AddUserOnSignupAcceptedController {

  private final UserRepository userRepository = PersistenceContext.repositories().users();

  private final EventPublisher dispatcher = PubSubRegistry.publisher();

  /**
   * @param theSignupRequest
   *
   * @return
   *
   * @throws ConcurrencyException
   * @throws IntegrityViolationException
   */
  public SystemUser addUser(final SignupAcceptedEvent theSignupRequest) {

    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    userBuilder.withUsername(theSignupRequest.username()).withPassword(theSignupRequest.password())
        .withName(theSignupRequest.name()).withEmail(theSignupRequest.email())
        .withRoles(ClientRoles.STUDENT);
    final SystemUser newUser = userRepository.save(userBuilder.build());

    // notify interested parties
    final DomainEvent event = new NewUserRegisteredFromSignupEvent(
        theSignupRequest.mecanographicNumber(), newUser.username());
    dispatcher.publish(event);

    return newUser;
  }
}
