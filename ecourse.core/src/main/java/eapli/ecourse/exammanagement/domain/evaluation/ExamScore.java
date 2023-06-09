package eapli.ecourse.exammanagement.domain.evaluation;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * The score of a exam/section/question.
 */
public class ExamScore implements ValueObject, Comparable<ExamScore> {
  public static final long serialVersionUID = 1L;

  private Double score;

  /**
   * Constructor for Score.
   *
   * @param score score of the section
   */
  private ExamScore(final Double score) {
    this.score = score;
  }

  /**
   * Empty constructor for ORM.
   */
  protected ExamScore() {

  }

  public static ExamScore valueOf(final Double score) {
    return new ExamScore(score);
  }

  /**
   * Returns the string representation of a Score.
   *
   * @return score of the section
   */
  @Override
  public String toString() {
    return Double.toString(this.score);
  }

  public Double value() {
    return this.score;
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
    return Double.compare(this.score, other.score);
  }
}
