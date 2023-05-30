package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.visitor.Visitor;

@SuppressWarnings({ "squid:S106" })
public class MeetingParticipantPrinter implements Visitor<InviteDTO> {

  public void visit(final InviteDTO visitee, final SystemUser authenticatedUser) {

    System.out.printf("%-30s%-14s",
        visitee.getUser().username() + (visitee.getUser().equals(authenticatedUser) ? "*" : "") +
            (visitee.getUser().equals(visitee.getMeeting().scheduledBy()) ? "(Owner)" : ""),
        visitee.getStatus().toString());
  }

  @Override
  public void visit(InviteDTO visitee) {
    System.out.printf("%-30%s-14s", visitee.getUser().username(), visitee.getStatus().toString());
  }

}
