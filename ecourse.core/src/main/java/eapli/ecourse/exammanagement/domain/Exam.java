package eapli.ecourse.exammanagement.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamState.State;
import eapli.ecourse.exammanagement.domain.dto.ExamDTO;
import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;

/**
 * Abstract class that describes an exam.
 */
@Entity
public abstract class Exam implements AggregateRoot<ExamCode> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private ExamCode code;

  @Column(nullable = false)
  private Course course;

  @Column(nullable = false)
  private Teacher teacher;

  @Column(nullable = false)
  private Identifier identifier;

  @Column(nullable = false)
  private Title title;

  @Column(nullable = true)
  private Description description;

  @Column(nullable = false)
  private ExamState state;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Section> sections;

  public Exam(final Course course, final Teacher teacher, final Identifier identifier, final Title title,
      final Description description) {
    this.code = ExamCode.newID();
    this.course = course;
    this.teacher = teacher;
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.state = new ExamState(State.DRAFT);
    this.sections = new ArrayList<Section>();
  }

  public Exam(final Course course, final Teacher teacher, final Identifier identifier, final Title title) {
    this.code = ExamCode.newID();
    this.course = course;
    this.teacher = teacher;
    this.identifier = identifier;
    this.title = title;
    this.state = new ExamState(State.DRAFT);
    this.sections = new ArrayList<Section>();
  }

  public Exam() {
    this.code = ExamCode.newID();
    this.course = null;
    this.teacher = null;
    this.identifier = null;
    this.title = null;
    this.description = null;
    this.state = new ExamState(State.DRAFT);
    this.sections = new ArrayList<Section>();
  }

  public void addSection(Section section) {
    this.sections.add(section);
  }

  public void addSections(Collection<Section> sections) {
    this.sections.addAll(sections);
  }

  public void changeCourse(Course course) {
    this.course = course;
  }

  public void changeTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public void changeIdentifier(Identifier identifier) {
    this.identifier = identifier;
  }

  public void changeTitle(Title title) {
    this.title = title;
  }

  public void changeDescription(Description description) {
    this.description = description;
  }

  public void changeState(ExamState state) {
    this.state = state;
  }

  @Override
  public ExamCode identity() {
    return this.code;
  }

  public abstract boolean sameAs(Object other);

  public Course course() {
    return this.course;
  }

  public ExamDTO toDto() {
    return new ExamDTO(this.code, this.course, this.teacher, this.identifier, this.title, this.description, this.state);
  }
}
