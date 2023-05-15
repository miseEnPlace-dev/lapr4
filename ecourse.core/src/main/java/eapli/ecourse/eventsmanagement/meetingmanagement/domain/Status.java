package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Status implements ValueObject {
  private static final long serialVersionUID = 1L;

  public enum InviteStatus {
    PENDING, ACCEPTED, REJECTED, NO_ANSWER
  }

  public Status() {
    state = InviteStatus.PENDING;
  }

  @Enumerated(EnumType.STRING)
  private InviteStatus state;

  public boolean isPending() {
    return this.state == InviteStatus.PENDING;
  }

  public boolean isAccepted() {
    return this.state == InviteStatus.ACCEPTED;
  }

  public boolean isRejected() {
    return this.state == InviteStatus.REJECTED;
  }

  public void accepted() {
    this.state = InviteStatus.ACCEPTED;
  }

  public void rejected() {
    this.state = InviteStatus.REJECTED;
  }

  public void noAnswer() {
    this.state = InviteStatus.NO_ANSWER;
  }
}
