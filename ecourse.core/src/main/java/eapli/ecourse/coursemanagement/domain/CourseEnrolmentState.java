package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class CourseEnrolmentState implements ValueObject {
  public enum EnrolmentState {
    OPEN, CLOSED
  }

  private EnrolmentState state;

  public CourseEnrolmentState(EnrolmentState state) {
    this.state = state;
  }

  public CourseEnrolmentState() {
    state = EnrolmentState.CLOSED;
  }

  public void changeToOpen() {
    state = EnrolmentState.OPEN;
  }

  public void changeToClosed() {
    state = EnrolmentState.CLOSED;
  }

  public boolean isOpen() {
    return state == EnrolmentState.OPEN;
  }

  public boolean isClosed() {
    return state == EnrolmentState.CLOSED;
  }

  public boolean isSameAs(EnrolmentState state) {
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
