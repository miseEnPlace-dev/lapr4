package eapli.ecourse.exammanagement.domain;

import java.util.List;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class FormativeExamBuilder implements DomainFactory<FormativeExam> {
  private FormativeExam exam;

  private Course course;
  private Teacher teacher;
  private QuestionIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
  private List<ExamSection> sections;

  public FormativeExamBuilder withCourse(Course course) {
    this.course = course;
    return this;
  }

  public FormativeExamBuilder withTeacher(Teacher teacher) {
    this.teacher = teacher;
    return this;
  }

  public FormativeExamBuilder withIdentifier(QuestionIdentifier identifier) {
    this.identifier = identifier;
    return this;
  }

  public FormativeExamBuilder withTitle(ExamTitle title) {
    this.title = title;
    return this;
  }

  public FormativeExamBuilder withDescription(ExamDescription description) {
    this.description = description;
    return this;
  }

  public FormativeExamBuilder withSections(List<ExamSection> sections) {
    this.sections = sections;
    return this;
  }

  private FormativeExam buildOrThrow() {
    if (exam != null)
      return exam;

    Preconditions.noneNull(course, teacher, identifier, title, description, sections);

    exam = new FormativeExam(course, teacher, identifier, title, description, sections);
    exam.publish();
    return exam;
  }

  @Override
  public FormativeExam build() {
    final FormativeExam ret = buildOrThrow();
    exam = null;
    return ret;
  }
}
