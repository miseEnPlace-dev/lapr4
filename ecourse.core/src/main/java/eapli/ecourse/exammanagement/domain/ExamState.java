package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

/**
 * Enum that represents the state of an exam.
 */
@Embeddable
public class ExamState implements ValueObject {
  /**
   * Enum that represents the state of an exam.
   */
  public enum State {
    DRAFT, PUBLISHED
  }

  @Enumerated(EnumType.STRING)
  private State state;

  /**
   * Constructor for the ExamState.
   *
   * @param state the state of the exam
   */
  public ExamState(State state) {
    this.state = state;
  }

  /**
   * Constructor for the ExamState.
   * Default state is DRAFT.
   */
  public ExamState() {
    state = State.DRAFT;
  }

  /**
   * Change the state of the exam to DRAFT.
   */
  public void changeToDraft() {
    state = State.DRAFT;
  }

  /**
   * Change the state of the exam to PUBLISHED.
   */
  public void changeToPublished() {
    state = State.PUBLISHED;
  }

  /**
   * Check if the exam is in DRAFT state.
   *
   * @return true if the exam is in DRAFT state, false otherwise
   */
  public boolean isDraft() {
    return state == State.DRAFT;
  }

  /**
   * Check if the exam is in PUBLISHED state.
   *
   * @return true if the exam is in PUBLISHED state, false otherwise
   */
  public boolean isPublished() {
    return state == State.PUBLISHED;
  }

  /**
   * Check if the exam is in the same state as the given state.
   *
   * @param state the state to compare
   * @return true if the exam is in the same state as the given state, false
   *         otherwise
   */
  public boolean isSameAs(State state) {
    return this.state == state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof ExamState))
      return false;
    ExamState examState = (ExamState) o;
    return this.isSameAs(examState.state);
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
