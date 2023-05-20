package eapli.ecourse.enrolmentmagement.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eapli.ecourse.enrolmentmagement.EnrolmentBaseTest;
import eapli.ecourse.enrolmentmanagement.domain.EnrolmentState;

public class EnrolmentStateTest extends EnrolmentBaseTest {
  @Test
  public void ensureStateIsCreatedWithPendingState() {
    EnrolmentState state = new EnrolmentState();
    assertTrue(state.isPending());
  }

  @Test
  public void ensureItIsPossibleToAcceptEnrolment() {
    EnrolmentState state = new EnrolmentState();
    state.changeToAccepted();
    assertTrue(state.isAccepted());
  }

  @Test
  public void ensureItIsPossibleToRejectEnrolment() {
    EnrolmentState state = new EnrolmentState();
    state.changeToRejected();
    assertTrue(state.isRejected());
  }

  @Test
  public void ensureItIsNotPossibleToChangeAlreadyAcceptedState() {
    EnrolmentState state = new EnrolmentState();
    state.changeToAccepted();
    assertTrue(state.isAccepted());
    assertThrows(IllegalStateException.class, () -> state.changeToRejected());
  }

  @Test
  public void ensureItIsNotPossibleToChangeAlreadyRejectedState() {
    EnrolmentState state = new EnrolmentState();
    state.changeToRejected();
    assertTrue(state.isRejected());
    assertThrows(IllegalStateException.class, () -> state.changeToAccepted());
  }

  @Test
  public void ensureToStringWorks() {
    EnrolmentState state = new EnrolmentState();
    assertTrue(state.toString().equals("PENDING"));
    EnrolmentState state2 = new EnrolmentState();
    state2.changeToAccepted();
    assertTrue(state2.toString().equals("ACCEPTED"));
    EnrolmentState state3 = new EnrolmentState();
    state3.changeToRejected();
    assertTrue(state3.toString().equals("REJECTED"));
  }

  @Test
  public void ensureTwoStatesAreEqualWithSameState() {
    EnrolmentState state = new EnrolmentState();
    EnrolmentState state2 = new EnrolmentState();
    assertTrue(state.equals(state2));
  }

  @Test
  public void ensureTwoStatesAreNotEqualWithDifferentState() {
    EnrolmentState state = new EnrolmentState();
    EnrolmentState state2 = new EnrolmentState();
    state2.changeToAccepted();
    assertTrue(!state.equals(state2));
  }
}
