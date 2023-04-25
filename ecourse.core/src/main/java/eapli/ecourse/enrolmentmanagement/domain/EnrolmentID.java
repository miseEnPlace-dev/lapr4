package eapli.ecourse.enrolmentmanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class EnrolmentID implements ValueObject, Comparable<EnrolmentID> {
  private static final long serialVersionUID = 1L;

  private String enrolmentId;

  private EnrolmentID(final String enrolmentId) {
    Preconditions.nonEmpty(enrolmentId);

    this.enrolmentId = UUID.fromString(enrolmentId).toString();
  }

  protected EnrolmentID() {
    // for ORM
    this.enrolmentId = null;
  }

  public static EnrolmentID valueOf(final String enrolmentId) {
    return new EnrolmentID(enrolmentId);
  }

  public static EnrolmentID newID() {
    return valueOf(UUID.randomUUID().toString());
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
