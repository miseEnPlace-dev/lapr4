package eapli.ecourse.coursemanagement.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;

public class CourseState implements ValueObject {
  public enum State {
    CLOSED, OPEN, IN_PROGRESS, FINISHED
  }

  @Enumerated(EnumType.STRING)
  private State state;

  public CourseState(State state) {
    this.state = state;
  }

  public CourseState() {
    state = State.CLOSED;
  }

  public void changeToClose() {
    state = State.CLOSED;
  }

  public void changeToOpen() {
    state = State.OPEN;
  }

  public void changeToInProgress() {
    state = State.IN_PROGRESS;
  }

  public void changeToFinished() {
    state = State.FINISHED;
  }

  public boolean isClosed() {
    return state == State.CLOSED;
  }

  public boolean isOpen() {
    return state == State.OPEN;
  }

  public boolean isInProgress() {
    return state == State.IN_PROGRESS;
  }

  public boolean isFinished() {
    return state == State.FINISHED;
  }

  public boolean isSameAs(State state) {
    return this.state == state;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof CourseState)) {
      return false;
    }
    CourseState courseState = (CourseState) o;
    return state == courseState.state;
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
