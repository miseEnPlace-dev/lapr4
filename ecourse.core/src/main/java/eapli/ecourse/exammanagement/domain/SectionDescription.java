package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * Section Description class.
 */
public class SectionDescription implements ValueObject, Comparable<SectionDescription> {
  private static final long serialVersionUID = 1L;

  private String description;

  /**
   * Constructor for SectionDescription.
   *
   * @param description description of the section
   */
  protected SectionDescription(final String description) {
    Preconditions.nonEmpty(description, "Section Description should neither be null nor empty.");

    if (description.length() > 255)
      throw new IllegalArgumentException("Section Description should not exceed 255 characters");

    this.description = description;
  }

  /**
   * Empty constructor for ORM.
   */
  protected SectionDescription() {
  }

  /**
   * Returns the string representation of a SectionDescription.
   *
   * @return description of the section
   */
  @Override
  public String toString() {
    return this.description;
  }

  /**
   * Compares two SectionDescription objects, comparing their descriptions.
   *
   * @param sectionDescription SectionDescription to compare
   * @return 0 if the descriptions are equal, greater than 0 if this description
   *         is greater than the other, less than 0 if this description is less
   *         than the other.
   */
  @Override
  public int compareTo(final SectionDescription sectionDescription) {
    return this.description.compareTo(sectionDescription.description);
  }
}
