package eapli.ecourse.classmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

@Entity
public class Class implements AggregateRoot<Long> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  // we are coupling a business concept with an implementation detail
  // until more info from the client, we will be using this solution
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private DayInWeek dayInWeek;
  private Duration duration;
  private SpecialClass specialClass;

  // @ManyToOne(optional = false)
  // private Teacher scheduledBy;

  public Class(final DayInWeek dayInWeek, final Duration duration,
      final SpecialClass specialClass) {
    Preconditions.noneNull(dayInWeek, duration);

    this.dayInWeek = dayInWeek;
    this.duration = duration;
    this.specialClass = specialClass;
  }

  protected Class() {
    // for ORM
  }

  @Override
  public boolean equals(final Object o) {
    return DomainEntities.areEqual(this, o);
  }

  @Override
  public boolean sameAs(Object other) {
    return DomainEntities.areEqual(this, other);
  }

  @Override
  public Long identity() {
    return this.id;
  }

  @Override
  public int hashCode() {
    return DomainEntities.hashCode(this);
  }

  public DayInWeek dayInWeek() {
    return this.dayInWeek;
  }

  public Duration duration() {
    return this.duration;
  }

  public SpecialClass specialClass() {
    return this.specialClass;
  }

  // public Teacher scheduledBy() {
  // return scheduledBy;
  // }
}
