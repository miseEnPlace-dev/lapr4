package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.framework.visitor.Visitor;

public class MeetingPrinter implements Visitor<MeetingDTO> {
  @Override
  public void visit(MeetingDTO visitee) {
    new MeetingHeader().printHeader();

    System.out.printf("%-7s%-10s%-10s%-14s%12s", visitee.getId(), visitee.getTime().toString(),
      visitee.getDuration(), visitee.getScheduledBy(), visitee.getCanceledAt());
  }
}
