package eapli.ecourse.exammanagement.domain.formative;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.teachermanagement.domain.Teacher;

@Entity
public class FormativeExam extends Exam {
  private static final long serialVersionUID = 1L;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Collection<FormativeExamSection> sections;

  public FormativeExam(Course course, Teacher teacher, ExamIdentifier identifier, ExamTitle title,
      ExamDescription description, ExamScore score,
      Collection<FormativeExamSection> sections) {
    super(course, teacher, identifier, title, description, score);

    this.sections = sections;
  }

  protected FormativeExam() {
    // for ORM only
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

    return that.identity().equals(this.identity()) && that.title().equals(this.title())
        && that.course().equals(this.course()) && that.teacher().equals(this.teacher())
        && that.description().equals(this.description())
        && that.state().equals(this.state());
  }
}
