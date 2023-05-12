package eapli.ecourse.coursemanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class CourseState implements ValueObject {
  private static final long serialVersionUID = 1L;

  public enum State {
    CLOSED, OPEN, IN_PROGRESS, FINISHED;
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
    changeTo(State.CLOSED);
  }

  public void changeToOpen() {
    changeTo(State.OPEN);
  }

  public void changeToInProgress() {
    changeTo(State.IN_PROGRESS);
  }

  public void changeToFinished() {
    changeTo(State.FINISHED);
  }

  private void changeTo(State state) {
    if (this.state == State.FINISHED)
      throw new IllegalStateException("Cannot toggle state of a finished course");

    this.state = state;
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
  public String toString() {
    return state.toString();
  }
}
