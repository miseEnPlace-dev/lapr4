package eapli.ecourse.app.common.console.presentation.meeting;

import java.util.List;

import eapli.ecourse.eventsmanagement.meetingmanagement.application.MeetingResponseController;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteStatus;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class MeetingResponseUI extends AbstractUI {

  private final MeetingResponseController ctrl = new MeetingResponseController(
      PersistenceContext.repositories().invites(), PersistenceContext.repositories().meetings());

  @Override
  protected boolean doShow() {
    List<InviteDTO> invites = this.ctrl.getInvites(null);

    if (!invites.iterator().hasNext()) {
      System.out.println("There are no registered invites.");
      return false;
    }

    SelectWidget<InviteDTO> selector = new SelectWidget<>("Invites:", invites,
        new InvitePrinter());
    final InviteDTO selected = selector.selectedElement();

    if (selected == null) {
      System.out.println("There are no invites to show.");
      return false;
    }

    System.out.println("Select one option:");
    System.out.println("1. Accept");
    System.out.println("2. Reject");
    System.out.println("0. Exit");

    Integer permission = Console.readOption(1, 2, 0);

    if (permission == 0) {
      return false;
    }

    System.out.println("\nDo you want to submit the data? [Y/N]");

    String confirm = "";
    while (!confirm.equals("Y") && !confirm.equals("N")) {
      confirm = Console.readLine("Option: ").toUpperCase();
    }

    if (confirm.equals("N")) {
      System.out.println("Operation Cancelled!");
      return false;
    }

    try {

      if (permission == 1) {
        this.ctrl.changeInviteStatus(selected, InviteStatus.Status.ACCEPTED);
        System.out.println("Invite accepted.");
        return false;
      }

      if (permission == 2) {
        this.ctrl.changeInviteStatus(selected, InviteStatus.Status.REJECTED);
        System.out.println("Invite rejected.");
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return false;
  }

  @Override
  public String headline() {
    return "Accept/Reject invite";
  }

}
