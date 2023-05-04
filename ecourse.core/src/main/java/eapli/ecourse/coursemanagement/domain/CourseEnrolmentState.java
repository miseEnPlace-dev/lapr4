package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class CourseEnrolmentState implements ValueObject {
  public enum State {
    OPEN, CLOSED
  }

  private State state;

  public CourseEnrolmentState(State state) {
    this.state = state;
  }

  public CourseEnrolmentState() {
    state = State.CLOSED;
  }

  public void changeToOpen() {
    state = State.OPEN;
  }

  public void changeToClosed() {
    state = State.CLOSED;
  }

  public boolean isOpen() {
    return state == State.OPEN;
  }

  public boolean isClosed() {
    return state == State.CLOSED;
  }

  public boolean isSameAs(State state) {
    return this.state == state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof CourseEnrolmentState))
      return false;

    final CourseEnrolmentState that = (CourseEnrolmentState) o;
    return state.equals(that.state);
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
