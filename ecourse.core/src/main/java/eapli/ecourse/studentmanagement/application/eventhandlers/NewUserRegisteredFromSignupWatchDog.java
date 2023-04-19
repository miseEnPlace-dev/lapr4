package eapli.ecourse.studentmanagement.application.eventhandlers;

import eapli.ecourse.studentmanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.pubsub.EventHandler;
import eapli.framework.validations.Preconditions;

/**
 *
 * @author Paulo Gandra de Sousa
 */
public class NewUserRegisteredFromSignupWatchDog implements EventHandler {

  /*
   * (non-Javadoc)
   *
   * @see eapli.framework.domain.events.EventHandler#onEvent(eapli.framework.
   * domain.
   * events.DomainEvent)
   */
  @Override
  public void onEvent(final DomainEvent domainevent) {
    Preconditions.ensure(domainevent instanceof NewUserRegisteredFromSignupEvent);

    final NewUserRegisteredFromSignupEvent event = (NewUserRegisteredFromSignupEvent) domainevent;

    final var controller = new AddClientUserOnSignupAcceptedController();
    controller.addClientUser(event);
  }
}
