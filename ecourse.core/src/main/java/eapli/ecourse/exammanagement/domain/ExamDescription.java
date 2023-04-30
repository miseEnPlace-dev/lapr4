package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;

import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * Exam Description class.
 */
public class ExamDescription implements ValueObject, Comparable<ExamDescription> {
  private static final long serialVersionUID = 1L;

  private String description;

  /**
   * Constructor for ExamDescription.
   *
   * @param description description of the exam
   */
  protected ExamDescription(final String description) {
    if (StringPredicates.isNullOrEmpty(description))
      throw new IllegalArgumentException("Exam Description should neither be null nor empty");

    // TODO description validations

    this.description = description;
  }

  /**
   * Empty constructor for ORM.
   */
  protected ExamDescription() {
  }

  /**
   * Returns the string representation of a ExamDescription.
   *
   * @return description of the exam
   */
  @Override
  public String toString() {
    return this.description;
  }

  /**
   * Compares two ExamDescription objects, comparing their descriptions.
   *
   * @param examDescription ExamDescription to compare
   * @return 0 if the descriptions are equal, greater than 0 if this description
   *         is greater than the other, less than 0 if this description is less
   *         than the other.
   */
  @Override
  public int compareTo(final ExamDescription examDescription) {
    return this.description.compareTo(examDescription.description);
  }
}
