package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.eventsmanagement.meetingmanagement.application.ScheduleMeetingController;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

public class ScheduleMeetingUI extends AbstractUI {

  private final ScheduleMeetingController theController = new ScheduleMeetingController(null,
      AuthzRegistry.authorizationService());

  @Override
  protected boolean doShow() {
    System.out.println("Schedule Meeting: ");

    return false;
  }

  @Override
  public String headline() {
    return "Schedule Meeting";
  }

}
