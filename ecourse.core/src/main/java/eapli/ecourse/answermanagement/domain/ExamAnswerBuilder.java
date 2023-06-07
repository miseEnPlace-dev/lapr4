package eapli.ecourse.answermanagement.domain;

import java.util.ArrayList;
import java.util.List;

import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class ExamAnswerBuilder implements DomainFactory<ExamAnswer> {
  private ExamAnswer examAnswer;

  private List<AnswerToQuestion> answers;
  private Student student;
  private Exam exam;

  public void withAnswer(AnswerToQuestion answer) {
    if (this.answers == null)
      this.answers = new ArrayList<>();
    this.answers.add(answer);
  }

  public void withStudent(Student student) {
    this.student = student;
  }

  public void withExam(Exam exam) {
    this.exam = exam;
  }

  private ExamAnswer buildOrThrow() {
    if (examAnswer != null)
      return examAnswer;

    Preconditions.noneNull(answers, student, exam);

    examAnswer = new ExamAnswer(student, exam, answers);
    return examAnswer;
  }

  @Override
  public ExamAnswer build() {
    final ExamAnswer ret = buildOrThrow();
    examAnswer = null;
    return ret;
  }
}
