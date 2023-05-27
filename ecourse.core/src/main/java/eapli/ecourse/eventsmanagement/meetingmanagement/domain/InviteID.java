package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

@Embeddable
public class InviteID implements ValueObject, Comparable<InviteID> {
  private static final long serialVersionUID = 1L;

  private String inviteId;

  private InviteID(final String inviteId) {
    Preconditions.nonEmpty(inviteId);

    this.inviteId = UUID.fromString(inviteId).toString();
  }

  protected InviteID() {
    // for ORM
    this.inviteId = null;
  }

  public static InviteID valueOf(final String inviteId) {
    return new InviteID(inviteId);
  }

  public static InviteID newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.inviteId;
  }

  @Override
  public int compareTo(final InviteID arg0) {
    return inviteId.compareTo(arg0.inviteId);
  }
}
