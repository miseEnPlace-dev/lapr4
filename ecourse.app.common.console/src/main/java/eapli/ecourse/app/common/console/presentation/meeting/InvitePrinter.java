package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.framework.visitor.Visitor;

@SuppressWarnings({ "squid:S106" })
public class InvitePrinter implements Visitor<InviteDTO> {

  @Override
  public void visit(final InviteDTO visitee) {
    String timeString = String.format("%02d:%02d", visitee.getTime().hour(), visitee.getTime().minute());

    System.out.printf("%-7s%-10s%-10s%-5s",
        visitee.getMeeting().scheduledBy().username(),
        visitee.getStatus().toString(),
        timeString,
        visitee.getTime().dayInWeek());
  }

}
