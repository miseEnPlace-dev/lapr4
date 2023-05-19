package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * Exam/Section Description class.
 */
public class ExamDescription implements ValueObject, Comparable<ExamDescription> {
  private static final long serialVersionUID = 1L;

  private String description;

  /**
   * Constructor for Description.
   *
   * @param description description of the exam/section
   */
  private ExamDescription(final String description) {
    Preconditions.nonNull(description, "Description should neither be null nor empty.");

    if (description.length() > 255)
      throw new IllegalArgumentException("Description should not exceed 255 characters");

    this.description = description;
  }

  protected ExamDescription() {
    // for ORM only
  }

  public static ExamDescription valueOf(final String description) {
    return new ExamDescription(description);
  }

  /**
   * Returns the string representation of a Description.
   *
   * @return description of the exam
   */
  @Override
  public String toString() {
    return this.description;
  }

  /**
   * Compares two Description objects, comparing their descriptions.
   *
   * @param other Description to compare
   * @return 0 if the descriptions are equal, greater than 0 if this description
   *         is greater than the other, less than 0 if this description is less
   *         than the other.
   */
  @Override
  public int compareTo(final ExamDescription other) {
    return this.description.compareTo(other.description);
  }
}