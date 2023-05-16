package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * The score of a exam/section/question.
 */
public class Score implements ValueObject, Comparable<Score> {
  public static final long serialVersionUID = 1L;

  private int score;

  /**
   * Constructor for Score.
   *
   * @param score score of the section
   */
  private Score(final int score) {
    Preconditions.isPositive(score, "Score should be positive");

    this.score = score;
  }

  /**
   * Empty constructor for ORM.
   */
  protected Score() {

  }

  public static Score valueOf(final int score) {
    return new Score(score);
  }

  /**
   * Returns the string representation of a Score.
   *
   * @return score of the section
   */
  @Override
  public String toString() {
    return Integer.toString(this.score);
  }

  /**
   * Compares two Score objects, comparing their scores.
   *
   * @param other Score to compare
   * @return 0 if the scores are equal, greater than 0 if this score is greater
   *         than the other, less than 0 if this score is less than the other.
   */
  @Override
  public int compareTo(final Score other) {
    return Integer.compare(this.score, other.score);
  }
}
