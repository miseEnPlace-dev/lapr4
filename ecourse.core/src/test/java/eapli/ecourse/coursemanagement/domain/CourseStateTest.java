package eapli.ecourse.coursemanagement.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CourseStateTest {
  @Test
  public void ensureDefaultStateIsClosed() {
    CourseState state = new CourseState();
    assertTrue(state.isClosed());
  }

  @Test
  public void ensureNextIsWorking() {
    CourseState state = new CourseState();
    assertTrue(state.isClosed());
    state.next();
    assertTrue(state.isOpen());
    state.next();
    assertTrue(state.isInProgress());
    state.next();
    assertTrue(state.isFinished());
  }

  @Test
  public void ensureNextItIsNotPossibleToGoToNextStateInFinished() {
    CourseState state = new CourseState(CourseState.State.FINISHED);
    assertThrows(IllegalStateException.class, () -> state.next());
  }

  @Test
  public void ensureItIsNotPossibleToGoToPreviousStateInClosed() {
    CourseState state = new CourseState(CourseState.State.CLOSED);
    assertThrows(IllegalStateException.class, () -> state.previous());
  }

  @Test
  public void ensureItIsNotPossibleToGoToPreviousStateInInProgress() {
    CourseState state = new CourseState(CourseState.State.IN_PROGRESS);
    assertThrows(IllegalStateException.class, () -> state.previous());
  }

  @Test
  public void ensureItIsNotPossibleToGoToPreviousStateInFinished() {
    CourseState state = new CourseState(CourseState.State.FINISHED);
    assertThrows(IllegalStateException.class, () -> state.previous());
  }

  @Test
  public void ensureItIsPossibleToGoToPreviousStateInOpen() {
    CourseState state = new CourseState(CourseState.State.OPEN);
    state.previous();
    assertTrue(state.isClosed());
  }

  @Test
  public void ensureItIsNotPossibleToChangeFinishedState() {
    CourseState state = new CourseState(CourseState.State.FINISHED);
    assertThrows(IllegalStateException.class, () -> state.changeToClosed());
  }

  @Test
  public void ensureToStringIsWorking() {
    CourseState state = new CourseState(CourseState.State.OPEN);
    assertTrue(state.toString().contains("OPEN"));
  }
}
