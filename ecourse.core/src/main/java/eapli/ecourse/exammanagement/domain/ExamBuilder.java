package eapli.ecourse.exammanagement.domain;

import java.util.List;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class ExamBuilder implements DomainFactory<EvaluationExam> {
  private EvaluationExam exam;

  private ExamCode code;
  private Course course;
  private Teacher teacher;
  private Identifier identifier;
  private Title title;
  private Description description;
  private ExamState state;
  private List<Section> sections;
  private Time startTime;
  private Time endTime;
  private ExamInfo feedbackInfo;
  private ExamInfo gradeInfo;
  private Score score;

  public ExamBuilder withCode(ExamCode code) {
    this.code = code;
    return this;
  }

  public ExamBuilder withCourse(Course course) {
    this.course = course;
    return this;
  }

  public ExamBuilder withTeacher(Teacher teacher) {
    this.teacher = teacher;
    return this;
  }

  public ExamBuilder withIdentifier(Identifier identifier) {
    this.identifier = identifier;
    return this;
  }

  public ExamBuilder withTitle(Title title) {
    this.title = title;
    return this;
  }

  public ExamBuilder withDescription(Description description) {
    this.description = description;
    return this;
  }

  public ExamBuilder withState(ExamState state) {
    this.state = state;
    return this;
  }

  public ExamBuilder withSections(List<Section> sections) {
    this.sections = sections;
    return this;
  }

  public ExamBuilder withStartTime(Time startTime) {
    this.startTime = startTime;
    return this;
  }

  public ExamBuilder withEndTime(Time endTime) {
    this.endTime = endTime;
    return this;
  }

  public ExamBuilder withFeedbackInfo(ExamInfo feedbackInfo) {
    this.feedbackInfo = feedbackInfo;
    return this;
  }

  public ExamBuilder withGradeInfo(ExamInfo gradeInfo) {
    this.gradeInfo = gradeInfo;
    return this;
  }

  public ExamBuilder withScore(Score score) {
    this.score = score;
    return this;
  }

  private EvaluationExam buildOrThrow() {
    if (exam != null)
      return exam;

    Preconditions.noneNull(code, course, teacher, identifier, title, description, state, sections);

    exam = new EvaluationExam(course, teacher, identifier, title, description, state, sections, startTime, endTime,
        feedbackInfo, gradeInfo, score);
    return exam;
  }

  @Override
  public EvaluationExam build() {
    final EvaluationExam ret = buildOrThrow();
    exam = null;
    return ret;
  }
}
