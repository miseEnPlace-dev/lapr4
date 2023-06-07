package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.framework.visitor.Visitor;

public class MeetingPrinter implements Visitor<MeetingDTO> {
  @Override
  public void visit(MeetingDTO visitee) {
    System.out.printf("  %-22s%-16s%-17s%-14s", visitee.getTime(),
        visitee.getDuration(), visitee.getScheduledBy().username(), visitee.getCanceledAt());
  }
}
