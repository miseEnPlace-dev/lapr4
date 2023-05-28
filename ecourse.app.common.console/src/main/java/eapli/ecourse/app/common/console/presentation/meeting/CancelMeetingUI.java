package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.eventsmanagement.meetingmanagement.application.CancelMeetingController;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.NoSuchElementException;

public class CancelMeetingUI extends AbstractUI {

  private final CancelMeetingController ctrl = new CancelMeetingController(AuthzRegistry.authorizationService(),
    PersistenceContext.repositories().meetings(), PersistenceContext.repositories().invites());

  @Override
  protected boolean doShow() {

    Iterable<MeetingDTO> userMeetings = ctrl.listNotCanceledScheduledMeetings();
    if (!userMeetings.iterator().hasNext()) {
      System.out.println("You have no scheduled meetings");
      return false;
    }

    final SelectWidget<MeetingDTO> selector = new SelectWidget<>("Scheduled Meetings: ", userMeetings,
      new MeetingPrinter());

    selector.show();
    final MeetingDTO selected = selector.selectedElement();
    if (selected == null)
      return false;

    try {
      ctrl.cancelMeeting(selected);
    } catch (NoSuchElementException exception) {
      System.out.println("There is no meeting with the given ID");
      return false;
    }

    System.out.println("Meeting Canceled");
    return false;
  }

  @Override
  public String headline() {
    return "Cancel Meeting";
  }
}
