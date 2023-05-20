package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.eventsmanagement.application.ScheduleAvailabilityService;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.ExtraordinaryClassRepository;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class ScheduleMeetingController {
  private final MeetingRepository meetingRepository;
  private final InviteRepository inviteRepository;
  private final AuthorizationService authz;
  private final ScheduleAvailabilityService sAvaService;

  public ScheduleMeetingController(final MeetingRepository meetingRepository, final AuthorizationService authz,
      final InviteRepository inviteRepository, final CourseClassRepository classRepository,
      final ExtraordinaryClassRepository extraordinaryClassRepository, final EnrolmentRepository enrolmentRepository,
      final StudentRepository studentRepository, final TeacherRepository teacherRepository) {
    this.meetingRepository = meetingRepository;
    this.authz = authz;
    this.inviteRepository = inviteRepository;
    this.sAvaService = new ScheduleAvailabilityService(classRepository, extraordinaryClassRepository, inviteRepository,
        enrolmentRepository, studentRepository, teacherRepository);
  }

  public Meeting scheduleMeeting(Time time, Duration duration, Iterable<SystemUser> users) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT, ClientRoles.POWER_USER, ClientRoles.MANAGER,
        ClientRoles.TEACHER);

    Preconditions.noneNull(time, duration);

    Meeting meeting = new Meeting(time, duration);

    if (meetingRepository.containsOfIdentity(meeting.identity()))
      throw new IllegalStateException("There is already a meeting with that id.");
    Meeting m = saveMeeting(meeting);

    sendInvites(users, m);

    return m;
  }

  private void sendInvites(Iterable<SystemUser> users, Meeting meeting) {
    for (SystemUser user : users) {
      Invite invite = new Invite(meeting, user);

      saveInvite(invite);
    }
  }

  private Meeting saveMeeting(Meeting meeting) {
    if (meeting == null)
      throw new IllegalArgumentException("Meeting cannot be null.");

    return meetingRepository.save(meeting);
  }

  private void saveInvite(Invite invite) {
    if (invite == null)
      throw new IllegalArgumentException();

    inviteRepository.save(invite);
  }

  public boolean checkIfUsersAreAvailable(Time meetingTime, Duration meetingDuration,
      Iterable<SystemUser> selectedUsers) {

    if (sAvaService.areAllAvailable(selectedUsers, meetingTime, meetingDuration))
      return true;

    return false;
  }

  public SystemUser getAuthenticatedUser() {
    return authz.loggedinUserWithPermissions(ClientRoles.TEACHER).orElseThrow(IllegalStateException::new);
  }
}
