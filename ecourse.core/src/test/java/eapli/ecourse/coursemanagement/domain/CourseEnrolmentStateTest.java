package eapli.ecourse.coursemanagement.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState.EnrolmentState;

public class CourseEnrolmentStateTest {
  @Test
  public void ensureCourseEnrolmentStateIsCreatedInClosedState() {
    CourseEnrolmentState state = new CourseEnrolmentState();
    assertTrue(state.isClosed());
  }

  @Test
  public void ensureItIsPossibleToToggle() {
    CourseEnrolmentState state = new CourseEnrolmentState();
    assertTrue(state.isClosed());
    state.toggle();
    assertTrue(state.isOpen());
  }

  @Test
  public void ensureSameAsWorks() {
    CourseEnrolmentState state = new CourseEnrolmentState();
    assertTrue(state.isSameAs(EnrolmentState.CLOSED));
  }

  @Test
  public void ensureToStringWorks() {
    CourseEnrolmentState state = new CourseEnrolmentState();
    assertTrue(state.toString().equals("CLOSED"));
    state.toggle();
    assertTrue(state.toString().equals("OPEN"));
  }
}
