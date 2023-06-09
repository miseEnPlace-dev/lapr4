package eapli.ecourse.postitmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PostItState implements ValueObject {
  public enum State {
    ACTIVE, DELETED
  }

  @Enumerated(EnumType.STRING)
  private State state;

  public PostItState(State state) {
    this.state = state;
  }

  public PostItState() {
    state = State.ACTIVE;
  }

  public boolean isDeleted() {
    return state == State.DELETED;
  }

  public boolean isActive() {
    return state == State.ACTIVE;
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
