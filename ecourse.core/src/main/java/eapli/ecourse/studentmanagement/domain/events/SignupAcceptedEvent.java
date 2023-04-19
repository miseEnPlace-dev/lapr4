package eapli.ecourse.studentmanagement.domain.events;

import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.SignupRequest;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * This is the event that is published when a {@link SignupRequest} is accepted.
 * <p>
 * It is published by an
 * {@link eapli.framework.infrastructure.eventpubsub.EventPublisher}, in this
 * case inside
 * {@link eapli.ecourse.studentmanagement.application.AcceptRefuseSignupRequestControllerEventfulImpl}.
 * <p>
 * Afterwards it is processed by any
 * {@link eapli.framework.infrastructure.eventpubsub.EventHandler}
 * that may consume these types of events (e.g.
 * {@link eapli.ecourse.usermanagement.application.eventhandlers.SignupAcceptedWatchDog}.
 *
 * @author Paulo Gandra de Sousa
 */
public class SignupAcceptedEvent extends DomainEventBase {

  private static final long serialVersionUID = 1L;

  private final SignupRequest theSignupRequest;

  public SignupAcceptedEvent(final SignupRequest theSignupRequest) {
    this.theSignupRequest = theSignupRequest;
  }

  public Username username() {
    return theSignupRequest.username();
  }

  public Password password() {
    return theSignupRequest.password();
  }

  public Name name() {
    return theSignupRequest.name();
  }

  public EmailAddress email() {
    return theSignupRequest.email();
  }

  public MecanographicNumber mecanographicNumber() {
    return theSignupRequest.mecanographicNumber();
  }

  @Override
  public String toString() {
    return "SignupAccepted(" + username() + ")";
  }
}
