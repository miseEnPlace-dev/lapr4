package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@Entity
public class Meeting implements AggregateRoot<MeetingID> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Column(nullable = false)
  private Duration duration;

  @Column
  private Canceled canceledAt;

  @Column(nullable = false)
  private Time time;

  @EmbeddedId
  private MeetingID id;

  @Column(nullable = false)
  private Set<SystemUser> participants;

  protected Meeting() {
    // for ORM
  }

  public Meeting(final Time time, final Duration duration, final Set<SystemUser> participants) {
    Preconditions.noneNull(time, duration, participants);

    this.time = time;
    this.duration = duration;
    this.participants = participants;
    this.canceledAt = null;
  }

  @Override
  public boolean equals(Object other) {
    return DomainEntities.areEqual(this, other);
  }

  @Override
  public boolean sameAs(final Object other) {
    if (!(other instanceof Meeting))
      return false;

    final Meeting that = (Meeting) other;

    if (this == that)
      return true;

    return time().equals(that.time()) && duration().equals(that.duration());
  }

  @Override
  public int hashCode() {
    return DomainEntities.hashCode(this);
  }

  public Time time() {
    return this.time;
  }

  public Duration duration() {
    return this.duration;
  }

  public Set<SystemUser> participants() {
    return this.participants;
  }

  public Canceled canceledAt() {
    return this.canceledAt;
  }

  public void cancel() {
    this.canceledAt = new Canceled(Calendar.getInstance());
  }

  @Override
  public MeetingID identity() {
    return this.id;
  }
}
