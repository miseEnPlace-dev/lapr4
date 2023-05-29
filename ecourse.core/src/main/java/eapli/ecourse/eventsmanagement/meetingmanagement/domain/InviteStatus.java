package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class InviteStatus implements ValueObject {
  private static final long serialVersionUID = 1L;

  public enum Status {
    PENDING, ACCEPTED, REJECTED, CANCELED, NO_ANSWER
  }

  public InviteStatus() {
    state = Status.PENDING;
  }

  public InviteStatus(Status state) {
    this.state = state;
  }

  @Enumerated(EnumType.STRING)
  private Status state;

  public boolean isPending() {
    return this.state == Status.PENDING;
  }

  public boolean isAccepted() {
    return this.state == Status.ACCEPTED;
  }

  public boolean isRejected() {
    return this.state == Status.REJECTED;
  }

  public boolean isNoAnswer() {
    return this.state == Status.NO_ANSWER;
  }

  public boolean isCanceled() {
    return this.state == Status.CANCELED;
  }

  public void accept() {
    this.state = Status.ACCEPTED;
  }

  public void reject() {
    this.state = Status.REJECTED;
  }

  public void noAnswer() {
    this.state = Status.NO_ANSWER;
  }

  public void cancel() { this.state = Status.CANCELED; }

  @Override
  public String toString() {
    return state.toString();
  }
}
