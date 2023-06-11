package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class ListMeetingControllerTest {

  private ListMeetingController controller;
  private MeetingRepository meetingRepository;
  private InviteRepository inviteRepository;
  private AuthorizationService authzRegistry;

  private SystemUser getDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  private Meeting getDummyMeeting() {
    Calendar c = Calendar.getInstance();
    return new Meeting(Time.valueOf(c), Duration.valueOf(120), getDummyUser());
  }

  private Invite getDummyInvite() {
    return new Invite(getDummyMeeting(), getDummyUser());
  }

  @Before
  public void setup() {
    authzRegistry = mock(AuthorizationService.class);
    meetingRepository = mock(MeetingRepository.class);
    inviteRepository = mock(InviteRepository.class);
    controller = new ListMeetingController(meetingRepository, inviteRepository, authzRegistry);
  }

  @Test
  public void ensureUsersInvitedToMeetingAreCorrect() {
    Invite invite = getDummyInvite();
    Iterable<InviteDTO> invites = controller.getMeetingDetails(invite.meeting().toDto());

    for (InviteDTO inviteDTO : invites) {
      assertEquals(inviteDTO.getUser(), invite.user().toDTO());
    }
  }

  @Test
  public void ensureMeetingDetailsAreCorrect() {
    Meeting meeting = getDummyMeeting();
    Iterable<InviteDTO> invites = controller.getMeetingDetails(meeting.toDto());

    for (InviteDTO inviteDTO : invites) {
      assertEquals(inviteDTO.getMeeting(), meeting.toDto());
    }
  }

  @Test
  public void ensureInviteStatusIsCorrect() {
    Invite invite = getDummyInvite();
    Iterable<InviteDTO> invites = controller.getMeetingDetails(invite.meeting().toDto());

    for (InviteDTO inviteDTO : invites) {
      assertEquals(inviteDTO.getStatus(), invite.status().toString());
    }
  }
}
