package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

@Entity
public class Invite implements AggregateRoot<MeetingID> {
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private InviteStatus status;

  @ManyToOne
  private Meeting meeting;

  @ManyToOne
  private User user;

  protected Invite() {
    // ORM
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof Invite))
      return false;

    final Invite that = (Invite) other;

    if (this == that)
      return true;

    return meeting().equals(that.meeting()) && user().equals(that.user()) && status().equals(that.status());
  }

  public Meeting meeting() {
    return this.meeting;
  }

  public User user() {
    return this.user;
  }

  public InviteStatus status() {
    return this.status;
  }

  @Override
  public MeetingID identity() {
    return this.identity();
  }

  @Override
  public boolean equals(Object other) {
    return DomainEntities.areEqual(this, other);
  }

  @Override
  public int hashCode() {
    return DomainEntities.hashCode(this);
  }

}
