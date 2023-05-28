package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InviteStatusTest {

  @Test
  public void ensureInviteStatusIsPending() {
    InviteStatus status = new InviteStatus();
    assertTrue(status.isPending());
  }

  @Test
  public void ensureInviteStatusIsAccepted() {
    InviteStatus status = new InviteStatus();
    status.accept();
    assertTrue(status.isAccepted());
  }

  @Test
  public void ensureInviteStatusIsRejected() {
    InviteStatus status = new InviteStatus();
    status.reject();
    assertTrue(status.isRejected());
  }

  @Test
  public void ensureInviteStatusIsComparable() {
    InviteStatus status1 = new InviteStatus();
    InviteStatus status2 = new InviteStatus();
    assertEquals(status1, status2);
  }

  @Test
  public void ensureInviteStatusIsComparableWithDifferentStatus() {
    InviteStatus status1 = new InviteStatus();
    InviteStatus status2 = new InviteStatus();
    status2.accept();
    assertNotEquals(status1, status2);
  }

  @Test
  public void ensureInviteStatusHashCodeIsWorking() {
    InviteStatus status1 = new InviteStatus();
    InviteStatus status2 = new InviteStatus();
    assertEquals(status1.hashCode(), status2.hashCode());
  }

  @Test
  public void ensureInviteStatusHashCodeIsWorkingWithDifferentStatus() {
    InviteStatus status1 = new InviteStatus();
    InviteStatus status2 = new InviteStatus();
    status2.accept();
    assertNotEquals(status1.hashCode(), status2.hashCode());
  }

  @Test
  public void ensureInviteStatusCanEqual() {
    InviteStatus status1 = new InviteStatus();
    InviteStatus status2 = new InviteStatus();
    assertTrue(status1.canEqual(status2));
  }

}
