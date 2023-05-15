package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@Entity
public class Invite implements AggregateRoot<InviteID> {
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private InviteID id;

  @Column(nullable = false)
  private InviteStatus status;

  @Column(nullable = false)
  private Meeting meeting;

  @Column(nullable = false)
  private SystemUser user;

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

  public SystemUser user() {
    return this.user;
  }

  public InviteStatus status() {
    return this.status;
  }

  @Override
  public InviteID identity() {
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
