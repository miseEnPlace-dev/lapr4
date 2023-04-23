package eapli.ecourse.enrolmentmanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class EnrolmentID implements ValueObject, Comparable<EnrolmentID> {
  private static final long serialVersionUID = 1L;

  private String enrolmentId;

  protected EnrolmentID(final String enrolmentId) {
    if (StringPredicates.isNullOrEmpty(enrolmentId))
      throw new IllegalArgumentException("Enrolment ID should neither be null nor empty");

    this.enrolmentId = enrolmentId;
  }

  protected EnrolmentID() {
    // for ORM
  }

  public static EnrolmentID valueOf(final String enrolmentId) {
    return new EnrolmentID(enrolmentId);
  }

  @Override
  public String toString() {
    return this.enrolmentId;
  }

  @Override
  public int compareTo(final EnrolmentID arg0) {
    return enrolmentId.compareTo(arg0.enrolmentId);
  }
}
