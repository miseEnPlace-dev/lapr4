package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MeetingID implements ValueObject, Comparable<MeetingID> {
  private static final long serialVersionUID = 1L;

  private String enrolmentId;

  private MeetingID(final String enrolmentId) {
    Preconditions.nonEmpty(enrolmentId);

    this.enrolmentId = UUID.fromString(enrolmentId).toString();
  }

  protected MeetingID() {
    // for ORM
    this.enrolmentId = null;
  }

  public static MeetingID valueOf(final String enrolmentId) {
    return new MeetingID(enrolmentId);
  }

  public static MeetingID newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.enrolmentId;
  }

  @Override
  public int compareTo(final MeetingID arg0) {
    return enrolmentId.compareTo(arg0.enrolmentId);
  }
}
