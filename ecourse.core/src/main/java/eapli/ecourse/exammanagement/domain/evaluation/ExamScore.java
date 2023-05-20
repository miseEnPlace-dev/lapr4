package eapli.ecourse.exammanagement.domain.evaluation;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * The score of a exam/section/question.
 */
public class ExamScore implements ValueObject, Comparable<ExamScore> {
  public static final long serialVersionUID = 1L;

  private int score;

  /**
   * Constructor for Score.
   *
   * @param score score of the section
   */
  private ExamScore(final int score) {
    Preconditions.isPositive(score, "Score should be positive");

    this.score = score;
  }

  /**
   * Empty constructor for ORM.
   */
  protected ExamScore() {

  }

  public static ExamScore valueOf(final int score) {
    return new ExamScore(score);
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
  public int compareTo(final ExamScore other) {
    return Integer.compare(this.score, other.score);
  }
}
