package eapli.ecourse.coursemanagement.domain;

import java.util.Calendar;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

@Entity
public class Course implements AggregateRoot<CourseCode> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private CourseCode code;

  private CourseTitle title;
  private CourseDescription description;
  private EnrolmentLimits enrolmentLimits;
  private boolean isOpen;
  private boolean isAcceptingEnrolments;
  private Calendar createdAt;

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

  public CourseCode code() {
    return identity();
  }

  public CourseTitle title() {
    return this.title;
  }

  public CourseDescription description() {
    return this.description;
  }

  public boolean isOpen() {
    return this.isOpen;
  }

  public boolean isAcceptingEnrolments() {
    return this.isAcceptingEnrolments;
  }

  public Calendar createdAt() {
    return this.createdAt;
  }

  public EnrolmentLimits enrolmentLimits() {
    return this.enrolmentLimits;
  }

  @Override
  public CourseCode identity() {
    return this.code;
  }
}
