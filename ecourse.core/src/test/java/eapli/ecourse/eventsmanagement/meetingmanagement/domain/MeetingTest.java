package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

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
    return new Meeting(Time.valueOf(c), Duration.valueOf(120), getDummyUser());
  }

  private Meeting getDummyMeeting2() {
    Calendar c = Calendar.getInstance();
    return new Meeting(Time.valueOf(c), Duration.valueOf(100), getDummyUser());
  }

  private Invite getDummyInvite() {
    return new Invite(getDummyMeeting(), getDummyUser());
  }

  @Test
  public void ensureItsPossibleToCreateInvite() {
    Invite invite = getDummyInvite();
    assertEquals(invite.user(), getDummyUser());
    assertTrue(invite.status().isPending());
  }

  @Test
  public void ensureItsPossibleToCancelMeeting() {
    Meeting meeting = getDummyMeeting();
    meeting.cancel();
    assertNotNull(meeting.canceledAt());
  }

  @Test
  public void ensureItsPossibleToAcceptInvite() {
    Invite invite = getDummyInvite();
    invite.status().accept();
    assertTrue(invite.status().isAccepted());
  }

  @Test
  public void ensureIdToStringIsWorking() {
    Calendar c = Calendar.getInstance();

    Meeting meeting = new Meeting(MeetingID.valueOf("123"), Time.valueOf(c), Duration.valueOf(120), getDummyUser());
    assertEquals("123", meeting.identity().toString());
  }

  @Test
  public void ensureMeetingIsComparable() {
    Meeting meeting1 = getDummyMeeting();
    Meeting meeting2 = getDummyMeeting2();
    assertFalse(meeting1.equals(meeting2));
  }

  @Test
  public void ensureMeetingIsComparableWithItself() {
    Meeting meeting1 = getDummyMeeting();
    assertTrue(meeting1.equals(meeting1));
  }

  @Test
  public void ensureMeetingHashCodeIsWorking() {
    Meeting meeting1 = getDummyMeeting();
    Meeting meeting2 = getDummyMeeting2();
    assertTrue(meeting1.hashCode() != meeting2.hashCode());
  }

  @Test
  public void ensureIdIsComparable() {
    MeetingID id1 = MeetingID.valueOf("123");
    MeetingID id2 = MeetingID.valueOf("122");
    MeetingID id3 = MeetingID.valueOf("124");
    assertTrue(id1.compareTo(id2) > 0);
    assertTrue(id1.compareTo(id3) < 0);
  }

  @Test
  public void ensureIdIsComparableWithItself() {
    MeetingID id1 = MeetingID.valueOf("123");
    assertTrue(id1.compareTo(id1) == 0);
  }

  @Test
  public void ensureIdIsEqual() {
    MeetingID id1 = MeetingID.valueOf("123");
    MeetingID id2 = MeetingID.valueOf("123");
    assertTrue(id1.equals(id2));
  }

  @Test
  public void ensureIdIsNotEqual() {
    MeetingID id1 = MeetingID.valueOf("123");
    MeetingID id2 = MeetingID.valueOf("122");
    assertTrue(!id1.equals(id2));
  }

  @Test
  public void ensureHashCodeIsWorking() {
    MeetingID id1 = MeetingID.valueOf("123");
    MeetingID id2 = MeetingID.valueOf("123");
    assertTrue(id1.hashCode() == id2.hashCode());
  }

  @Test
  public void ensureCanEqualIsWorking() {
    MeetingID id1 = MeetingID.valueOf("123");
    MeetingID id2 = MeetingID.valueOf("123");
    assertTrue(id1.canEqual(id2));
  }

  @Test
  public void ensureSameAsIsWorking() {
    assertFalse(getDummyMeeting().sameAs(getDummyMeeting2()));
  }

  @Test
  public void ensureSameAsIsWorkingWithOtherObject() {
    assertFalse(getDummyMeeting().sameAs(getDummyUser()));
  }

  @Test
  public void ensureSameAsIsWorkingWithSameObject() {
    Meeting meeting = getDummyMeeting();
    assertTrue(meeting.sameAs(meeting));
  }

  @Test
  public void ensureToDtoIsWorking() {
    Meeting meeting = getDummyMeeting();
    MeetingDTO dto = meeting.toDto();
    assertEquals(meeting.identity(), dto.getId());
    assertEquals(meeting.time(), dto.getTime());
    assertEquals(meeting.duration(), dto.getDuration());
    assertEquals(meeting.scheduledBy(), dto.getScheduledBy());
    assertEquals(meeting.canceledAt(), dto.getCanceledAt());
  }
}
