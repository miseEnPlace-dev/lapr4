package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import java.util.Set;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class ScheduleMeetingController {
  private final MeetingRepository meetingRepository;
  private final AuthorizationService authz;

  public ScheduleMeetingController(final MeetingRepository meetingRepository, final AuthorizationService authz) {
    this.meetingRepository = meetingRepository;
    this.authz = authz;
  }

  public Meeting scheduleMeeting(Time time, Duration duration, Set<SystemUser> users) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT, ClientRoles.POWER_USER, ClientRoles.MANAGER,
        ClientRoles.TEACHER);

    Preconditions.noneNull(time, duration);

    Meeting meeting = new Meeting(time, duration);

    sendInvites(users, meeting);

    if (meetingRepository.containsOfIdentity(meeting.identity()))
      throw new IllegalStateException("There is already a meeting with that id.");

    return saveMeeting(meeting);
  }

  private void sendInvites(Set<SystemUser> users, Meeting meeting) {
    for (SystemUser user : users) {
      Invite invite = new Invite(meeting, user);

      if (invite.status().isPending())
        throw new IllegalStateException("There is already an invite sent to that user.");

    }
  }

  private Meeting saveMeeting(Meeting meeting) {
    if (meeting == null)
      throw new IllegalArgumentException();

    return meetingRepository.save(meeting);
  }
}
