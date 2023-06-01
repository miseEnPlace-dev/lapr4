package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class ExtraordinaryClassID implements ValueObject, Comparable<ExtraordinaryClassID> {
  private static final long serialVersionUID = 1L;

  private String extraordinaryClassId;

  private ExtraordinaryClassID(final String extraordinaryClassId) {
    Preconditions.nonEmpty(extraordinaryClassId);

    this.extraordinaryClassId = extraordinaryClassId;
  }

  protected ExtraordinaryClassID() {
    // for ORM
    this.extraordinaryClassId = null;
  }

  public static ExtraordinaryClassID valueOf(final String meetingId) {
    return new ExtraordinaryClassID(meetingId);
  }

  public static ExtraordinaryClassID newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.extraordinaryClassId;
  }

  @Override
  public int compareTo(final ExtraordinaryClassID arg0) {
    return extraordinaryClassId.compareTo(arg0.extraordinaryClassId);
  }
}
