package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
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

  @Test
  public void ensureInviteCanBeRejected() {
    Invite invite = getDummyInvite();
    invite.reject();
    assertTrue(invite.status().isRejected());
  }

  @Test
  public void ensureInviteCanBeAccepted() {
    Invite invite = getDummyInvite();
    invite.accept();
    assertTrue(invite.status().isAccepted());
  }

  @Test
  public void ensureInviteSameAsIsWorking() {
    Invite invite = getDummyInvite();
    assertTrue(invite.sameAs(invite));
  }

  @Test
  public void ensureInviteSameAsIsWorkingWithNull() {
    Invite invite = getDummyInvite();
    assertTrue(!invite.sameAs(null));
  }

  @Test
  public void ensureInviteSameAsIsWorkingWithDifferentClass() {
    Invite invite = getDummyInvite();
    assertTrue(!invite.sameAs("dummy"));
  }

  @Test
  public void ensureInviteSameAsIsWorkingWithDifferentInvite() {
    Invite invite = getDummyInvite();
    Invite invite2 = new Invite(getDummyMeeting(), getDummyUser());
    assertFalse(invite.sameAs(invite2));
  }

  @Test
  public void ensureInviteSameAsIsWorkingWithSameInvite() {
    Invite invite = getDummyInvite();
    Invite invite2 = new Invite(getDummyMeeting(), getDummyUser());
    assertFalse(invite.sameAs(invite2));
  }

  @Test
  public void ensureInviteMeetingIsTheSame() {
    Invite invite = getDummyInvite();
    Invite invite2 = new Invite(getDummyMeeting(), getDummyUser());
    assertFalse(invite.meeting().equals(invite2.meeting()));
  }

  @Test
  public void ensureInviteUserIsTheSame() {
    Invite invite = getDummyInvite();
    Invite invite2 = new Invite(getDummyMeeting(), getDummyUser());
    assertTrue(invite.user().equals(invite2.user()));
  }

  @Test
  public void ensureInviteHashCodeIsWorking() {
    Invite invite = getDummyInvite();
    Invite invite2 = new Invite(getDummyMeeting(), getDummyUser());
    assertFalse(invite.hashCode() == invite2.hashCode());
  }

  @Test
  public void ensureInviteToDTOIsWorking() {
    Invite invite = getDummyInvite();
    InviteDTO inviteDTO = invite.toDto();
    assertTrue(inviteDTO.equals(invite.toDto()));
  }

  @Test
  public void ensureInviteEqualsIsWorking() {
    Invite invite = getDummyInvite();
    Invite invite2 = new Invite(getDummyMeeting(), getDummyUser());
    assertFalse(invite.equals(invite2));
  }

  @Test
  public void ensureInviteEqualsIsWorkingWithNull() {
    Invite invite = getDummyInvite();
    assertFalse(invite.equals(null));
  }

  @Test
  public void ensureInviteEqualsIsWorkingWithDifferentClass() {
    Invite invite = getDummyInvite();
    assertFalse(invite.equals("dummy"));
  }

  @Test
  public void ensureInviteIdentityIsWorking() {
    Invite invite = getDummyInvite();
    Invite invite2 = new Invite(getDummyMeeting(), getDummyUser());
    assertFalse(invite.identity().equals(invite2.identity()));
  }
}
