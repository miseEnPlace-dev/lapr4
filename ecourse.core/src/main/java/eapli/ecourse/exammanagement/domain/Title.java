package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
/**
 * Exam/Section Title class.
 */
public class Title implements ValueObject, Comparable<Title> {
  private static final long serialVersionUID = 1L;

  private String title;

  /**
   * Constructor for Title.
   *
   * @param title title of the section
   */
  protected Title(final String title) {
    Preconditions.nonEmpty(title, "Title should neither be null nor empty.");

    if (title.length() > 255)
      throw new IllegalArgumentException("Title should not exceed 255 characters");

    this.title = title;
  }

  /**
   * Empty constructor for ORM.
   */
  protected Title() {
  }

  /**
   * Returns the string representation of a Title.
   *
   * @return title of the section
   */
  @Override
  public String toString() {
    return this.title;
  }

  /**
   * Compares two Title objects, comparing their titles.
   *
   * @param other Title to compare
   * @return 0 if the titles are equal, greater than 0 if this title
   *         is greater than the other, less than 0 if this title is less
   *         than the other.
   */
  @Override
  public int compareTo(final Title other) {
    return this.title.compareTo(other.title);
  }
}
