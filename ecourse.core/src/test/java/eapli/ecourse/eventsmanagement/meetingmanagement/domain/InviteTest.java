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

    return new Meeting(Time.valueOf(c), Duration.valueOf(120), getDummyUser());
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
  public void testInviteSameAsInstance() {
    Invite invite = getDummyInvite();
    assertTrue(invite.sameAs(invite));
  }

  @Test
  public void ensureInviteIDIsComparable() {
    InviteID invite1 = InviteID.newID();
    InviteID invite2 = InviteID.valueOf(invite1.toString());
    assertTrue(invite1.compareTo(invite2) == 0);
  }

  @Test
  public void ensureInviteIDHashCodeWorks() {
    InviteID invite1 = InviteID.newID();
    InviteID invite2 = InviteID.valueOf(invite1.toString());
    assertTrue(invite1.hashCode() == invite2.hashCode());
  }

  @Test
  public void ensureInviteIDToStringWorks() {
    InviteID invite1 = InviteID.newID();
    InviteID invite2 = InviteID.valueOf(invite1.toString());
    assertTrue(invite1.toString().equals(invite2.toString()));
  }

  @Test
  public void ensureInviteIDIsNotEqualToNull() {
    InviteID invite1 = InviteID.newID();
    InviteID invite2 = null;
    assertTrue(!invite1.equals(invite2));
  }

  @Test
  public void ensureInviteIDIsNotEqualToAnotherClass() {
    InviteID invite1 = InviteID.newID();
    String invite2 = "invite";
    assertTrue(!invite1.equals(invite2));
  }

  @Test
  public void ensureInviteIDIsEqualToItself() {
    InviteID invite1 = InviteID.newID();
    assertTrue(invite1.equals(invite1));
  }

  @Test
  public void ensureInviteIDCanEqualWorks() {
    InviteID invite1 = InviteID.newID();
    InviteID invite2 = InviteID.valueOf(invite1.toString());
    assertTrue(invite1.canEqual(invite2));
  }
}
