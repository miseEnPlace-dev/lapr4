package eapli.ecourse.exammanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamState.State;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

/**
 * Abstract class that describes an exam.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Exam implements AggregateRoot<ExamCode> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private ExamCode code;

  @ManyToOne
  private Course course;

  @ManyToOne
  private Teacher teacher;

  @Column(nullable = false)
  private ExamIdentifier identifier;

  @Column(nullable = false)
  private ExamTitle title;

  @Column(nullable = true)
  private ExamDescription description;

  @Column(nullable = false)
  private ExamState state;

  protected Exam() {
    // for ORM only
  }

  public Exam(Course course, Teacher teacher, ExamIdentifier identifier, ExamTitle title,
      ExamDescription description) {
    Preconditions.noneNull(course, teacher, identifier, title, description);

    this.code = ExamCode.newID();
    this.course = course;
    this.teacher = teacher;
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.state = new ExamState(State.DRAFT);
  }

  @Override
  public ExamCode identity() {
    return this.code;
  }

  public Teacher teacher() {
    return this.teacher;
  }

  public ExamTitle title() {
    return this.title;
  }

  public ExamDescription description() {
    return this.description;
  }

  public ExamState state() {
    return this.state;
  }

  public abstract boolean sameAs(Object other);

  public Course course() {
    return this.course;
  }

  public void publish() {
    this.state.changeToPublished();
  }

  public ExamIdentifier identifier() {
    return this.identifier;
  }

  @Override
  public String toString() {
    return "Exam [code=" + code + ", course=" + course + ", description=" + description + ", identifier=" + identifier
        + ", state=" + state + ", teacher=" + teacher + ", title=" + title + "]";
  }
}
