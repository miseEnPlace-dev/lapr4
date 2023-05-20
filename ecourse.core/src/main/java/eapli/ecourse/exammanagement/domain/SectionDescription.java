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
public class SectionDescription implements ValueObject {
  private static final long serialVersionUID = 1L;

  private String description;

  /**
   * Constructor for Description.
   *
   * @param description description of the exam/section
   */
  private SectionDescription(final String description) {
    Preconditions.nonEmpty(description, "Description should neither be null nor empty.");

    if (description.length() > 255)
      throw new IllegalArgumentException("Description should not exceed 255 characters");

    this.description = description;
  }

  protected SectionDescription() {
    // for ORM only
  }

  public static SectionDescription valueOf(final String description) {
    return new SectionDescription(description);
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
}
