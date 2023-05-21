package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
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
  private Teacher scheduledBy;

  @ManyToOne(optional = false)
  private Course course;

  @ManyToMany
  @AttributeOverride(name = "id", column = @Column(name = "student_id"))
  public Set<Student> students;

  public ExtraordinaryClass(final Duration duration, final Time time, final Teacher scheduledBy,
      final Set<Student> students, final Course course) {
    Preconditions.noneNull(duration, time, scheduledBy, students, course);

    this.id = ExtraordinaryClassID.newID();
    this.duration = duration;
    this.time = time;
    this.scheduledBy = scheduledBy;
    this.students = students;
    this.course = course;
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

  public Course course() {
    return this.course;
  }

  public Set<Student> students() {
    return this.students;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof ExtraordinaryClass)) {
      return false;
    }

    final ExtraordinaryClass that = (ExtraordinaryClass) other;
    if (this == that) {
      return true;
    }

    return identity().equals(that.identity()) && duration.equals(that.duration) && time.equals(that.time)
        && scheduledBy.equals(that.scheduledBy) && students.equals(that.students) && course.equals(that.course);
  }

  @Override
  public ExtraordinaryClassID identity() {
    return this.id;
  }

  public Teacher scheduledBy() {
    return this.scheduledBy;
  }
}
