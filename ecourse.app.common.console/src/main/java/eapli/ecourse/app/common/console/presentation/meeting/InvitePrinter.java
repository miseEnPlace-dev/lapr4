package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.framework.visitor.Visitor;

@SuppressWarnings({ "squid:S106" })
public class InvitePrinter implements Visitor<InviteDTO> {

  @Override
  public void visit(final InviteDTO visitee) {
    System.out.printf("%-10s%-15s%-20s%", visitee.getId(), visitee.getStatus(), visitee.getTime());
  }

}
