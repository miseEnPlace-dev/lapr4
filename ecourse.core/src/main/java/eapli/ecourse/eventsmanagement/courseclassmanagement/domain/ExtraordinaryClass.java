package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

@Entity
public class ExtraordinaryClass implements AggregateRoot<ExtraordinaryClassID> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private ExtraordinaryClassID id;

  @Column(nullable = false)
  private Duration duration;

  @Column(nullable = false)
  private Time time;

  @ManyToOne(optional = false)
  public Teacher scheduledBy;

  public ExtraordinaryClass(final Duration duration, final Time time) {
    Preconditions.noneNull(duration, time);

    this.id = ExtraordinaryClassID.newID();
    this.duration = duration;
    this.time = time;
  }

  protected ExtraordinaryClass() {
    // for ORM
  }

  public Duration duration() {
    return this.duration;
  }

  public Time time() {
    return this.time;
  }

  @Override
  public boolean sameAs(Object other) {
    return DomainEntities.areEqual(this, other);
  }

  @Override
  public ExtraordinaryClassID identity() {
    return this.id;
  }

  public Teacher scheduledBy() {
    return this.scheduledBy;
  }
}
