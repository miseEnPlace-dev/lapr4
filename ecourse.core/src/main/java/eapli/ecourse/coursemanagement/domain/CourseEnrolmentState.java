package eapli.ecourse.coursemanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class CourseEnrolmentState implements ValueObject {
  private static final long serialVersionUID = 1L;

  public enum EnrolmentState {
    OPEN, CLOSED
  }

  @Enumerated(EnumType.STRING)
  private EnrolmentState state;

  public CourseEnrolmentState(EnrolmentState state) {
    this.state = state;
  }

  public CourseEnrolmentState() {
    state = EnrolmentState.CLOSED;
  }

  private void changeToOpen() {
    state = EnrolmentState.OPEN;
  }

  private void changeToClosed() {
    state = EnrolmentState.CLOSED;
  }

  public void toggle() {
    if (state == EnrolmentState.OPEN)
      changeToClosed();
    else
      changeToOpen();
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
  public String toString() {
    return state.toString();
  }
}
