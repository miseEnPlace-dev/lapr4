package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.security.core.Authentication;

public class CancelMeetingController {

  private final MeetingService service;

  private final AuthorizationService authz;

  public CancelMeetingController(AuthorizationService authz, MeetingRepository meetingRepository) {
    this.authz = authz;
    this.service = new MeetingService(meetingRepository);
  }

  public Iterable<MeetingDTO> listScheduledMeetings() {
    return this.service.meetingsOwnedBy(getAuthenticatedUser());
  }

  public SystemUser getAuthenticatedUser() {
    return authz.loggedinUserWithPermissions(ClientRoles.TEACHER, ClientRoles.MANAGER, ClientRoles.STUDENT)
      .orElseThrow(IllegalStateException::new);
  }

}
