package eapli.ecourse.exammanagement.domain.formative;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.teachermanagement.domain.Teacher;

public class FormativeExam extends Exam {
  private static final long serialVersionUID = 1L;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Collection<FormativeExamSection> sections;

  public FormativeExam(Course course, Teacher teacher, ExamIdentifier identifier, ExamTitle title,
      ExamDescription description,
      Collection<FormativeExamSection> sections) {
    super(course, teacher, identifier, title, description);

    this.sections = sections;
  }

  @Version
  private Long version;

  public Collection<FormativeExamSection> sections() {
    return sections;
  }

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
