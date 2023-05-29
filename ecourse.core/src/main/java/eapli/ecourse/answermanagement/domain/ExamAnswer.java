package eapli.ecourse.answermanagement.domain;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
}
