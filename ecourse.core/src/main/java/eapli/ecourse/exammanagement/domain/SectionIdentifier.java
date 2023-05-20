package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class SectionIdentifier implements ValueObject, Comparable<SectionIdentifier> {
  private static final long serialVersionUID = 1L;

  private String identifier;

  private SectionIdentifier(final String identifier) {
    Preconditions.nonEmpty(identifier, "Identifier should neither be null nor empty.");

    if (identifier.length() > 255)
      throw new IllegalArgumentException("Identifier should not exceed 255 characters");

    this.identifier = identifier;
  }

  protected SectionIdentifier() {
    // for ORM
  }

  public static SectionIdentifier valueOf(final String identifier) {
    return new SectionIdentifier(identifier);
  }

  @Override
  public String toString() {
    return this.identifier;
  }

  /**
   * Compares two Identifier objects, comparing their identifiers.
   *
   * @param other Identifier to compare
   * @return 0 if the identifiers are equal, greater than 0 if this identifier
   *         is greater than the other, less than 0 if this identifier is less
   *         than the other.
   */
  @Override
  public int compareTo(final SectionIdentifier other) {
    return this.identifier.compareTo(other.identifier);
  }
}
