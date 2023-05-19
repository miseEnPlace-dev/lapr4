package eapli.ecourse.exammanagement.domain;

import java.util.Collection;

import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.ecourse.teachermanagement.domain.Teacher;

public class FormativeExam extends Exam {
  private static final long serialVersionUID = 1L;

  public FormativeExam(Course course, Teacher teacher, QuestionIdentifier identifier, ExamTitle title,
      ExamDescription description,
      Collection<ExamSection> sections) {
    super(course, teacher, identifier, title, description, sections);
  }

  @Version
  private Long version;

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof FormativeExam))
      return false;

    final FormativeExam that = (FormativeExam) other;
    if (this == that)
      return true;

    // TODO: compare fields
    return super.identity().equals(that.identity());
  }
}
