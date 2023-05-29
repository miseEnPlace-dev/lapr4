package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.framework.visitor.Visitor;

@SuppressWarnings({ "squid:S106" })
public class MeetingParticipantPrinter implements Visitor<InviteDTO> {

  @Override
  public void visit(final InviteDTO visitee) {

    System.out.printf("%-20s%-14s", visitee.getUser().username(), visitee.getStatus().toString());
  }

}
