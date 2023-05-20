package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
public class SpecialClass implements DomainEntity<Long> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Id
  @GeneratedValue
  private Long id;

  private Time time;

  public SpecialClass(final Time time) {
    Preconditions.nonNull(time);

    this.time = time;
  }

  protected SpecialClass() {
    // for ORM
  }

  public Time time() {
    return this.time;
  }

  @Override
  public String toString() {
    return this.time.toString();
  }

  @Override
  public Long identity() {
    return this.id;
  }

  @Override
  public boolean sameAs(Object other) {
    return DomainEntities.areEqual(this, other);
  }

}
