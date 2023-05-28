package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import java.util.Set;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteStatus;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteStatus.Status;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class MeetingResponseController {
  private final InviteRepository inviteRepository;
  private final MeetingService meetingService;
  private final AuthorizationService authz;

  public MeetingResponseController(final InviteRepository inviteRepository, final MeetingRepository meetingRepository,
      final AuthorizationService authz) {
    this.inviteRepository = inviteRepository;
    this.meetingService = new MeetingService(meetingRepository, inviteRepository);
    this.authz = authz;
  }

  public Set<InviteDTO> getInvites(Username username) {
    return (Set<InviteDTO>) meetingService.getInvitesPending(username);
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

  public SystemUser getAuthenticatedUser() {
    return authz.loggedinUserWithPermissions(ClientRoles.TEACHER, ClientRoles.STUDENT, ClientRoles.MANAGER)
        .orElseThrow(IllegalStateException::new);
  }

  private void updateInvite(Invite invite) {
    inviteRepository.save(invite);
  }

}
