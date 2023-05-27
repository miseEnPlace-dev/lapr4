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

  private String meetingId;

  private MeetingID(final String meetingId) {
    Preconditions.nonEmpty(meetingId);

    this.meetingId = meetingId;
  }

  protected MeetingID() {
    // for ORM
    this.meetingId = null;
  }

  public static MeetingID valueOf(final String meetingId) {
    return new MeetingID(meetingId);
  }

  public static MeetingID newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.meetingId;
  }

  @Override
  public int compareTo(final MeetingID arg0) {
    return meetingId.compareTo(arg0.meetingId);
  }
}
