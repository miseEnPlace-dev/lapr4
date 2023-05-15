package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * The score of a question in an exam.
 */
public class SectionScore implements ValueObject, Comparable<SectionScore> {
  public static final long serialVersionUID = 1L;

  private int score;

  /**
   * Constructor for SectionScore.
   *
   * @param score score of the section
   */
  protected SectionScore(final int score) {
    Preconditions.isPositive(score, "Section Score should be positive");

    this.score = score;
  }

  /**
   * Empty constructor for ORM.
   */
  protected SectionScore() {

  }

  /**
   * Returns the string representation of a SectionScore.
   *
   * @return score of the section
   */
  @Override
  public String toString() {
    return Integer.toString(this.score);
  }

  /**
   * Compares two SectionScore objects, comparing their scores.
   *
   * @param sectionScore SectionScore to compare
   * @return 0 if the scores are equal, greater than 0 if this score is greater
   *         than the other, less than 0 if this score is less than the other.
   */
  @Override
  public int compareTo(final SectionScore sectionScore) {
    return Integer.compare(this.score, sectionScore.score);
  }
}
