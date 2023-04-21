package eapli.ecourse.extraordinaryclassmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

@Entity
public class ExtraordinaryClass implements AggregateRoot<Long> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Duration duration;
  private Time time;

  // @ManyToOne(optional = false)
  // public Teacher scheduledBy;

  public ExtraordinaryClass(final Duration duration, final Time time) {
    Preconditions.noneNull(duration, time);

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
  public Long identity() {
    return this.id;
  }

  // public Teacher scheduledBy() {
  // return this.scheduledBy;
  // }
}
