package eapli.ecourse.coursemanagement.domain;

import java.util.Calendar;
import java.util.Collection;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

@Entity
public class Course implements AggregateRoot<CourseCode> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private CourseCode code;

  @Column(nullable = false)
  private CourseTitle title;

  @Column(nullable = false)
  private CourseDescription description;

  @Column(nullable = false)
  private EnrolmentLimits enrolmentLimits;

  @AttributeOverride(name = "state", column = @Column(name = "courseState"))
  private CourseState courseState;

  @AttributeOverride(name = "state", column = @Column(name = "enrolmentState"))
  private CourseEnrolmentState enrolmentState;

  @Column(nullable = false)
  private Calendar createdAt;

  @ManyToOne
  private Teacher teacherInCharge;

  @OneToMany
  @AttributeOverride(name = "id", column = @Column(name = "teacher_id"))
  private Set<Teacher> teachers;

  protected Course() {
    // for ORM
  }

  public Course(final CourseCode code, final CourseTitle title, final CourseDescription description,
      final EnrolmentLimits enrolmentLimits, final CourseState courseState, final CourseEnrolmentState enrolmentState,
      Teacher teacher) {
    Preconditions.noneNull(code, title, description, enrolmentLimits, courseState, enrolmentState);

    this.code = code;
    this.title = title;
    this.description = description;
    this.enrolmentLimits = enrolmentLimits;
    this.courseState = courseState;
    this.enrolmentState = enrolmentState;
    this.teacherInCharge = teacher;
    this.createdAt = Calendar.getInstance();
  }

  public Course(final CourseCode code, final CourseTitle title, final CourseDescription description,
      final EnrolmentLimits enrolmentLimits, Teacher teacher) {
    Preconditions.noneNull(code, title, description, enrolmentLimits);

    this.code = code;
    this.title = title;
    this.description = description;
    this.enrolmentLimits = enrolmentLimits;
    this.courseState = new CourseState();
    this.enrolmentState = new CourseEnrolmentState();
    this.teacherInCharge = teacher;
    this.createdAt = Calendar.getInstance();
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
    if (!(other instanceof Course))
      return false;

    final Course that = (Course) other;

    if (this == that)
      return true;

    return code().equals(that.code()) && title().equals(that.title())
        && description().equals(that.description()) && enrolmentLimits().equals(that.enrolmentLimits())
        && state().equals(that.state()) && enrolmentState().equals(that.enrolmentState())
        && teacherInCharge().equals(that.teacherInCharge());
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

  public CourseState state() {
    return this.courseState;
  }

  public CourseEnrolmentState enrolmentState() {
    return this.enrolmentState;
  }

  public Calendar createdAt() {
    return this.createdAt;
  }

  public EnrolmentLimits enrolmentLimits() {
    return this.enrolmentLimits;
  }

  public Teacher teacherInCharge() {
    return this.teacherInCharge;
  }

  public Set<Teacher> teachers() {
    return this.teachers;
  }

  public CourseDTO toDto() {
    return new CourseDTO(this.code, this.title, this.description, this.enrolmentLimits, this.courseState,
        this.enrolmentState, this.createdAt);
  }

  public void toggleEnrolmentState() {
    if (courseState.isFinished())
      throw new IllegalStateException("Cannot toggle enrolment state of a finished course");

    if (courseState.isClosed())
      throw new IllegalStateException("Cannot toggle enrolment state of a closed course");

    enrolmentState.toggle();
  }

  public void nextState() {
    courseState.next();
  }

  public void previousState() {
    courseState.previous();
  }

  public void addTeachers(Iterable<Teacher> newTeachers) {
    Preconditions.noneNull(newTeachers);

    this.teachers.addAll((Collection<Teacher>) newTeachers);
  }

  public void addResponsibleTeacher(final Teacher teacher) {
    Preconditions.noneNull(teacher);

    this.teacherInCharge = teacher;
  }

  @Override
  public CourseCode identity() {
    return this.code;
  }
}
