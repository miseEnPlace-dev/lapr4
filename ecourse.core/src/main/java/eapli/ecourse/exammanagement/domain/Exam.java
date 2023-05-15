package eapli.ecourse.exammanagement.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;

/**
 * Abstract class that describes an exam.
 */
@Entity
public abstract class Exam implements AggregateRoot<Long> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private Course course;

  @Column(nullable = false)
  private Teacher teacher;

  @Column(nullable = false)
  private Identifier identifier;

  @Column(nullable = false)
  private Title title;

  @Column(nullable = false)
  private Description description;

  @Column(nullable = false)
  private ExamState state;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Section> sections;

  protected Exam() {
    // For ORM only
  }

  public Exam(final Course course, final Teacher teacher, final Identifier identifier, final Title title,
      final Description description, final ExamState state) {
    this.course = course;
    this.teacher = teacher;
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.state = state;
  }
}
