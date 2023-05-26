package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CancelMeetingController {

  MeetingRepository meetingRepository;

  InviteRepository inviteRepository;

  private final MeetingService service;

  private final AuthorizationService authz;

  public CancelMeetingController(AuthorizationService authz, MeetingRepository meetingRepository,
      InviteRepository inviteRepository) {
    this.authz = authz;
    this.meetingRepository = meetingRepository;
    this.service = new MeetingService(meetingRepository, inviteRepository);
    this.inviteRepository = inviteRepository;
  }

  public Iterable<MeetingDTO> listScheduledMeetings() {
    return this.service.meetingsScheduledBy(getAuthenticatedUser());
  }

  public void cancelMeeting(MeetingDTO meetingDTO) {
    meetingRepository.deleteOfIdentity(meetingDTO.getId());

    Iterable<Invite> invites = inviteRepository.findByMeetingID(meetingDTO.getId());
    invites.forEach(i -> inviteRepository.remove(i));
  }

  public SystemUser getAuthenticatedUser() {
    return authz.loggedinUserWithPermissions(ClientRoles.TEACHER, ClientRoles.MANAGER, ClientRoles.STUDENT)
        .orElseThrow(IllegalStateException::new);
  }

}