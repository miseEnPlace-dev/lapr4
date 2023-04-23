package eapli.ecourse.enrolmentmanagement.domain;

import java.util.Calendar;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

@Entity
public class Enrolment implements AggregateRoot<EnrolmentID> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  private Calendar createdAt;
  private Calendar updatedAt;
  private EnrolmentState state;

  @EmbeddedId
  private EnrolmentID id;

  protected Enrolment() {
    // for ORM
  }

  @Override
  public boolean equals(final Object o) {
    return DomainEntities.areEqual(this, o);
  }

  @Override
  public int hashCode() {
    return DomainEntities.hashCode(this);
  }

  @Override
  public boolean sameAs(final Object other) {
    return DomainEntities.areEqual(this, other);
  }

  @Override
  public EnrolmentID identity() {
    return this.id;
  }

  public EnrolmentID enrolmentId() {
    return this.id;
  }

  public Calendar createdAt() {
    return this.createdAt;
  }

  public Calendar updatedAt() {
    return this.updatedAt;
  }

  public EnrolmentState state() {
    return this.state;
  }
}
