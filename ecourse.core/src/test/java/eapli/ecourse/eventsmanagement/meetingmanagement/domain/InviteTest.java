package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class InviteTest {

  private SystemUser getDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  private Meeting getDummyMeeting() {
    // create a instance of calendar on the past
    Calendar c = Calendar.getInstance();
    c.add(Calendar.YEAR, -1);

    return new Meeting(Time.valueOf(c), Duration.valueOf(120));
  }

  private Invite getDummyInvite() {
    return new Invite(getDummyMeeting(), getDummyUser());
  }

  @Test
  public void testUserHasInvite() {
    Invite invite = getDummyInvite();
    assertEquals(invite.user(), getDummyUser());
  }

  @Test
  public void testInviteStatus() {
    Invite invite = getDummyInvite();
    assertTrue(invite.status().isPending());
  }

  @Test
  public void testInviteStatusChangeAcceptInvite() {
    Invite invite = getDummyInvite();
    invite.status().accept();
    assertTrue(invite.status().isAccepted());
  }

  @Test
  public void testInviteStatusChangeRejectInvite() {
    Invite invite = getDummyInvite();
    invite.status().reject();
    assertTrue(invite.status().isRejected());
  }

  @Test
  public void testInviteStatusChangeNoAnswer() {
    Invite invite = getDummyInvite();
    invite.status().noAnswer();
    assertTrue(invite.status().isNoAnswer());
  }

  @Test
  public void testInviteSameAsInstance() {
    Invite invite = getDummyInvite();
    assertTrue(invite.sameAs(invite));
  }
}
