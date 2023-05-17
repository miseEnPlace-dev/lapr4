package eapli.ecourse.eventsmanagement.domain.meetingmanagement.domain;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.ecourse.eventsmanagement.domain.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import eapli.ecourse.eventsmanagement.domain.Duration;

public class MeetingTest {

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
    return new Meeting(Time.valueOf(c), Duration.valueOf(120));
  }

  private Invite getDummyInvite() {
    return new Invite(getDummyMeeting(), getDummyUser());
  }

  @Test
  public void ensureItsPossibleToCreateMeeting() {
    Meeting meeting = getDummyMeeting();
    assertEquals(meeting.time(), getDummyMeeting().time());
    assertEquals(meeting.duration(), getDummyMeeting().duration());
  }

  @Test
  public void ensureItsPossibleToCreateInvite() {
    Invite invite = getDummyInvite();
    assertEquals(invite.user(), getDummyUser());
    assertEquals(invite.meeting(), getDummyMeeting());
    assertTrue(invite.status().isPending());
  }

  @Test
  public void ensureItsPossibleToCancelMeeting() {
    Meeting meeting = getDummyMeeting();
    meeting.cancel();
    assertNotNull(meeting.canceledAt());
  }
}
