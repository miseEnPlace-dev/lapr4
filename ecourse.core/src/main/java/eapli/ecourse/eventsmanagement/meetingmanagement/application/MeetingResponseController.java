package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import java.util.ArrayList;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteStatus;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteStatus.Status;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class MeetingResponseController {
  private final InviteRepository inviteRepository;
  private final MeetingRepository meetingRepository;
  private final MeetingService meetingService;

  public MeetingResponseController(final InviteRepository inviteRepository, final MeetingRepository meetingRepository) {
    this.inviteRepository = inviteRepository;
    this.meetingRepository = meetingRepository;
    this.meetingService = new MeetingService(meetingRepository, inviteRepository);
  }

  public ArrayList<InviteDTO> getInvites(Username username) {
    return (ArrayList<InviteDTO>) meetingService.getInvites(username);
  }

  public void changeInviteStatus(InviteDTO selected, Status status) {
    Invite invite = inviteRepository.findInviteById(selected.getId()).orElseThrow();

    if (status == InviteStatus.Status.ACCEPTED)
      invite.accept();
    else {
      invite.reject();
    }

    updateInvite(invite);
  }

  private void updateInvite(Invite invite) {
    inviteRepository.save(invite);
  }

}
