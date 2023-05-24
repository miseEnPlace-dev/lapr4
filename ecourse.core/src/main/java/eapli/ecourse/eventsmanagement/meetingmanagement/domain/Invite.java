package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@Entity
public class Invite implements AggregateRoot<InviteID> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private InviteID id;

  @Column(nullable = false)
  private InviteStatus status;

  @OneToOne
  private Meeting meeting;

  @OneToOne
  private SystemUser user;

  protected Invite() {
    // ORM
  }

  public Invite(final Meeting meeting, final SystemUser user) {
    this.meeting = meeting;
    this.user = user;
    this.status = new InviteStatus();
    this.id = InviteID.newID();
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

  public void accept() {
    this.status.accept();
  }

  public void reject() {
    this.status.reject();
  }

  @Override
  public InviteID identity() {
    return this.id;
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
