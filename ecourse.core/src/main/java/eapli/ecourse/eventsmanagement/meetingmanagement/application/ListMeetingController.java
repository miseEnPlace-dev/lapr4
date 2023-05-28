package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@UseCaseController
public class ListMeetingController {
  private AuthorizationService authz;
  private MeetingService service;

  public ListMeetingController(final MeetingRepository meetingRepository, final InviteRepository inviteRepository,
      final AuthorizationService authz) {
    this.authz = authz;
    this.service = new MeetingService(meetingRepository, inviteRepository);

  }

  public Iterable<MeetingDTO> getMeetingList() {
    return (Iterable<MeetingDTO>) this.service.notCanceledMeetingsScheduledBy(getAuthenticatedUser());
  }

  public Iterable<InviteDTO> getMeetingDetails(MeetingDTO selected) {
    return (Iterable<InviteDTO>) this.service.getAllInvitesByMeeting(selected.getId());

  }

  public SystemUser getAuthenticatedUser() {
    return authz.loggedinUserWithPermissions(ClientRoles.TEACHER, ClientRoles.STUDENT, ClientRoles.MANAGER)
        .orElseThrow(IllegalStateException::new);
  }

}
