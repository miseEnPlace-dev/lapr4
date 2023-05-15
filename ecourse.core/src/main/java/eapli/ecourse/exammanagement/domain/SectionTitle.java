package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * Section Title class.
 */
public class SectionTitle implements ValueObject, Comparable<SectionTitle> {
  private static final long serialVersionUID = 1L;

  private String title;

  /**
   * Constructor for SectionTitle.
   *
   * @param title title of the section
   */
  protected SectionTitle(final String title) {
    Preconditions.nonEmpty(title, "Section Description should neither be null nor empty.");

    if (title.length() > 255)
      throw new IllegalArgumentException("Section Description should not exceed 255 characters");

    this.title = title;
  }

  /**
   * Empty constructor for ORM.
   */
  protected SectionTitle() {
  }

  /**
   * Returns the string representation of a SectionTitle.
   *
   * @return title of the section
   */
  @Override
  public String toString() {
    return this.title;
  }

  /**
   * Compares two SectionTitle objects, comparing their titles.
   *
   * @param sectionTitle SectionTitle to compare
   * @return 0 if the titles are equal, greater than 0 if this title
   *         is greater than the other, less than 0 if this title is less
   *         than the other.
   */
  @Override
  public int compareTo(final SectionTitle sectionTitle) {
    return this.title.compareTo(sectionTitle.title);
  }
}
