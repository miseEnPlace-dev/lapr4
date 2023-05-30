package eapli.ecourse.answermanagement.domain;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import eapli.ecourse.answermanagement.dto.ExamAnswerDTO;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.framework.domain.model.AggregateRoot;

@Entity
public class ExamAnswer implements AggregateRoot<ExamAnswerId> {
  @EmbeddedId
  private ExamAnswerId id;

  @OneToMany
  private List<AnswerToQuestion> answers;

  @ManyToOne
  private Student student;

  @ManyToOne
  private Exam exam;

  public ExamAnswer(final Student student, final Exam exam, final List<AnswerToQuestion> answers) {
    this.id = ExamAnswerId.newID();
    this.student = student;
    this.exam = exam;
    this.answers = answers;
  }

  public ExamAnswer(final ExamAnswerId id, final Student student, final Exam exam,
      final List<AnswerToQuestion> answers) {
    this.id = id;
    this.student = student;
    this.exam = exam;
    this.answers = answers;
  }

  protected ExamAnswer() {
    // for ORM
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof ExamAnswer))
      return false;

    final ExamAnswer that = (ExamAnswer) other;
    if (this == that)
      return true;

    for (AnswerToQuestion answer : answers)
      if (!that.answers.contains(answer))
        return false;

    return identity().equals(that.identity());
  }

  @Override
  public ExamAnswerId identity() {
    return id;
  }

  public Student student() {
    return student;
  }

  public Exam exam() {
    return exam;
  }

  public List<AnswerToQuestion> answers() {
    return answers;
  }

  public Score score() {
    Score total = Score.valueOf(0.0);
    for (AnswerToQuestion answer : answers)
      total = total.add(answer.score());

    return total;
  }

  public ExamAnswerDTO toDto() {
    return new ExamAnswerDTO(student.identity().toString(), student.user().name().toString(), exam.title().toString(),
        exam.type(),
        score().toString());
  }
}
