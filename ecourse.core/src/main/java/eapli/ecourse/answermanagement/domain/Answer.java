package eapli.ecourse.answermanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.framework.domain.model.AggregateRoot;

@Entity
public class Answer implements AggregateRoot<AnswerId> {
  @EmbeddedId
  private AnswerId id;

  @Column(nullable = false)
  private ExamScore score;

  @ManyToOne
  private Student student;

  @ManyToOne
  private Exam exam;

  public Answer(final Student student, final Exam exam) {
    this.id = AnswerId.newID();
    this.student = student;
    this.exam = exam;
  }

  public Answer(final AnswerId id, final Student student, final Exam exam) {
    this.id = id;
    this.student = student;
    this.exam = exam;
  }

  protected Answer() {
    // for ORM
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof Answer))
      return false;

    final Answer that = (Answer) other;
    if (this == that)
      return true;

    return identity().equals(that.identity());
  }

  @Override
  public AnswerId identity() {
    return id;
  }

  public Student student() {
    return student;
  }

  public Exam exam() {
    return exam;
  }

  public ExamScore score() {
    return score;
  }

  public AnswerDTO toDto() {
    return new AnswerDTO(student.identity().toString(), student.user().name().toString(), exam.title().toString(),
        exam.type(),
        score().toString());
  }
}
