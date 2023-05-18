package eapli.ecourse.enrolmentmanagement.domain;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

@Entity
public class Enrolment implements AggregateRoot<EnrolmentID> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Column(nullable = false, updatable = false)
  private Calendar createdAt;

  @Column(nullable = false)
  private Calendar updatedAt;

  @Column(nullable = false)
  private EnrolmentState state;

  @EmbeddedId
  private EnrolmentID id;

  /**
   * cascade = CascadeType.NONE as the student is part of another aggregate
   */
  @XmlElement
  @JsonProperty
  @OneToOne(optional = false, cascade = CascadeType.ALL)
  private Student student;

  /**
   * cascade = CascadeType.NONE as the course is part of another aggregate
   */
  @XmlElement
  @JsonProperty
  @OneToOne(optional = false, cascade = CascadeType.ALL)
  private Course course;

  protected Enrolment() {
    // for ORM
  }

  public Enrolment(final EnrolmentID id, final Student student, final Course course) {
    Preconditions.noneNull(student, course);

    this.id = id;
    this.student = student;
    this.course = course;
    this.state = new EnrolmentState();
    this.createdAt = Calendar.getInstance();
    this.updatedAt = Calendar.getInstance();
  }

  public Enrolment(final Student student, final Course course) {
    Preconditions.noneNull(student, course);

    this.id = EnrolmentID.newID();
    this.student = student;
    this.course = course;
    this.state = new EnrolmentState();
    this.createdAt = Calendar.getInstance();
    this.updatedAt = Calendar.getInstance();
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
    if (!(other instanceof Enrolment))
      return false;

    final Enrolment that = (Enrolment) other;
    if (this == that)
      return true;

    return this.identity().equals(that.identity()) && this.student.equals(that.student)
        && this.course.equals(that.course);
  }

  public EnrolmentDTO toDto() {
    return new EnrolmentDTO(this.course.code().toString(), this.student.mecanographicNumber().toString(),
        this.student.user().name().toString(), this.createdAt, this.state.toString());
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

  public boolean isPending() {
    return state.isPending();
  }

  public boolean isAccepted() {
    return state.isAccepted();
  }

  public boolean isRejected() {
    return state.isRejected();
  }

  public Student student() {
    return this.student;
  }

  public Course course() {
    return this.course;
  }

  public void accept() {
    this.state.changeToAccepted();
    this.updatedAt = Calendar.getInstance();
  }

  public void reject() {
    this.state.changeToRejected();
    this.updatedAt = Calendar.getInstance();
  }
}
