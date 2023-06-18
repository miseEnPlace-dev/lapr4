package eapli.ecourse.app.common.console.presentation.meeting;

import java.util.NoSuchElementException;

import eapli.ecourse.eventsmanagement.meetingmanagement.application.CancelMeetingController;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CancelMeetingUI extends AbstractUI {


  @Override
  protected boolean doShow() {
   CancelMeetingController ctrl = new CancelMeetingController(AuthzRegistry.authorizationService(),
        PersistenceContext.repositories().meetings(), PersistenceContext.repositories().invites());

    Iterable<MeetingDTO> userMeetings = ctrl.listNotCanceledScheduledMeetings();
    if (!userMeetings.iterator().hasNext()) {
      System.out.println("You have no scheduled meetings");
      return false;
    }

    final SelectWidget<MeetingDTO> selector = new SelectWidget<>(new MeetingPrinter().header(), userMeetings,
        new MeetingPrinter());

    selector.show();
    final MeetingDTO selected = selector.selectedElement();
    if (selected == null)
      return false;

    String confirm = "";
    while (!confirm.equals("Y") && !confirm.equals("N"))
      confirm = Console.readLine("Do you want to confirm the cancellation of the meeting? [Y/N]").toUpperCase();

    if (confirm.equals("N")) {
      System.out.println("Operation Canceled");
      return false;
    }

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
    return "Cancel Scheduled Meeting";
  }
}
