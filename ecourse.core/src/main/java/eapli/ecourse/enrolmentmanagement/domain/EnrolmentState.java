package eapli.ecourse.enrolmentmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class EnrolmentState implements ValueObject {
  public enum State {
    PENDING, ACCEPTED, REJECTED
  }

  @Enumerated(EnumType.STRING)
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
  public String toString() {
    return state.toString();
  }
}
