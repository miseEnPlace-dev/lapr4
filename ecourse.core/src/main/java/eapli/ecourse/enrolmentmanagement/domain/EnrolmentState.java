package eapli.ecourse.enrolmentmanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class EnrolmentState implements ValueObject {
  public enum State {
    PENDING, ACCEPTED, REJECTED
  }

  private State state;

  public EnrolmentState(State state) {
    this.state = state;
  }

  public EnrolmentState() {
    state = State.PENDING;
  }

  public void changeToPending() {
    state = State.PENDING;
  }

  public void changeToAccepted() {
    state = State.ACCEPTED;
  }

  public void changeToRejected() {
    state = State.REJECTED;
  }

  public boolean isPending() {
    return state == State.PENDING;
  }

  public boolean isAccepted() {
    return state == State.ACCEPTED;
  }

  public boolean isRejected() {
    return state == State.REJECTED;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof EnrolmentState))
      return false;

    EnrolmentState that = (EnrolmentState) o;
    return state == that.state;
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
