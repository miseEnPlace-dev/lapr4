package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import java.util.ArrayList;

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
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class ScheduleMeetingController {
  private final MeetingRepository meetingRepository;
  private final InviteRepository inviteRepository;
  private final AuthorizationService authz;
  private final ScheduleAvailabilityService scheduleAvailableService;
  private final UserManagementService userManagementService;

  public ScheduleMeetingController(final MeetingRepository meetingRepository, final AuthorizationService authz,
      final InviteRepository inviteRepository, final CourseClassRepository classRepository,
      final ExtraordinaryClassRepository extraordinaryClassRepository, final EnrolmentRepository enrolmentRepository,
      final StudentRepository studentRepository, final TeacherRepository teacherRepository,
      final UserManagementService userManagementService) {
    this.meetingRepository = meetingRepository;
    this.authz = authz;
    this.inviteRepository = inviteRepository;
    this.scheduleAvailableService = new ScheduleAvailabilityService(classRepository, extraordinaryClassRepository,
        inviteRepository,
        enrolmentRepository, studentRepository, teacherRepository);
    this.userManagementService = userManagementService;
  }

  public Meeting scheduleMeeting(Time time, Duration duration, ArrayList<SystemUser> users) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT, ClientRoles.POWER_USER, ClientRoles.MANAGER,
        ClientRoles.TEACHER);
    SystemUser authenticatedUser = getAuthenticatedUser();
    Meeting m = scheduleMeeting(authenticatedUser, time, duration, users);

    return m;
  }

  public Meeting scheduleMeeting(SystemUser meetingOwner, Time time, Duration duration, ArrayList<SystemUser> users) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT, ClientRoles.POWER_USER, ClientRoles.MANAGER,
        ClientRoles.TEACHER);

    Preconditions.noneNull(meetingOwner, time, duration);

    Meeting meeting = new Meeting(time, duration, meetingOwner);

    if (meetingRepository.containsOfIdentity(meeting.identity()))
      throw new IllegalStateException("There is already a meeting with that id");

    Meeting m = saveMeeting(meeting);

    users.add(meetingOwner);

    sendInvites(users, m);

    return m;
  }

  private void sendInvites(Iterable<SystemUser> users, Meeting meeting) {
    for (SystemUser user : users) {
      Invite invite = new Invite(meeting, user);

      if (user.equals(getAuthenticatedUser()))
        invite.accept();

      saveInvite(invite);
    }
  }

  private Meeting saveMeeting(Meeting meeting) {
    if (meeting == null)
      throw new IllegalArgumentException("Meeting cannot be null");

    return meetingRepository.save(meeting);
  }

  private void saveInvite(Invite invite) {
    if (invite == null)
      throw new IllegalArgumentException("Invite cannot be null");

    inviteRepository.save(invite);
  }

  public boolean checkIfUsersAreAvailable(Time meetingTime, Duration meetingDuration,
      ArrayList<SystemUser> selectedUsers) {

    ArrayList<SystemUser> meetingUsers = new ArrayList<SystemUser>(selectedUsers);

    meetingUsers.add(getAuthenticatedUser());

    if (scheduleAvailableService.areAllAvailable(meetingUsers, meetingTime, meetingDuration))
      return true;

    return false;
  }

  public SystemUser getAuthenticatedUser() {
    return authz.loggedinUserWithPermissions(ClientRoles.TEACHER, ClientRoles.STUDENT, ClientRoles.MANAGER)
        .orElseThrow(IllegalStateException::new);
  }

  public ArrayList<SystemUser> getUsers() {
    ArrayList<SystemUser> allUsers = (ArrayList<SystemUser>) userManagementService.allUsers();

    allUsers.remove(getAuthenticatedUser());

    return allUsers;
  }
}
