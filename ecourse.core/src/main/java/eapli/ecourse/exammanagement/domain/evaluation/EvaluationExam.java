package eapli.ecourse.exammanagement.domain.evaluation;

import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamInfo;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.validations.Preconditions;

@Entity
public class EvaluationExam extends Exam {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ExamInfo feedbackInfo;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ExamInfo gradeInfo;

  @AttributeOverride(name = "time", column = @Column(name = "startTime"))
  private Time startTime;

  @AttributeOverride(name = "time", column = @Column(name = "endTime"))
  private Time endTime;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Collection<EvaluationExamSection> sections;

  public EvaluationExam(Course course, Teacher teacher, ExamIdentifier identifier, ExamTitle title,
      ExamDescription description, String fileContent, Collection<EvaluationExamSection> sections, Time startTime,
      Time endTime, ExamInfo feedbackInfo, ExamInfo gradeInfo, ExamScore score) {
    super(course, teacher, identifier, title, description, score, fileContent);

    Preconditions.noneNull(feedbackInfo, gradeInfo, startTime, endTime, score, sections);

    this.feedbackInfo = feedbackInfo;
    this.gradeInfo = gradeInfo;
    this.sections = sections;
    setDates(startTime, endTime);
  }

  public EvaluationExam(Course course, Teacher teacher, ExamIdentifier identifier, ExamTitle title,
      ExamDescription description, Collection<EvaluationExamSection> sections, Time startTime,
      Time endTime, ExamInfo feedbackInfo, ExamInfo gradeInfo, ExamScore score) {
    super(course, teacher, identifier, title, description, score);

    Preconditions.noneNull(feedbackInfo, gradeInfo, startTime, endTime, score, sections);

    this.feedbackInfo = feedbackInfo;
    this.gradeInfo = gradeInfo;
    this.sections = sections;
    setDates(startTime, endTime);
  }

  protected EvaluationExam() {
    // For ORM only
  }

  private void setDates(Time startTime, Time endTime) {
    if (endTime.compareTo(startTime) < 0)
      throw new IllegalArgumentException("End time must be after start time");

    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Time startTime() {
    return this.startTime;
  }

  public Time endTime() {
    return this.endTime;
  }

  public ExamInfo feedbackInfo() {
    return this.feedbackInfo;
  }

  public ExamInfo gradeInfo() {
    return this.gradeInfo;
  }

  @Override
  public String toString() {
    return super.toString() + "EvaluationExam [endTime=" + endTime + ", feedbackInfo=" + feedbackInfo + ", gradeInfo="
        + gradeInfo
        + ", startTime=" + startTime + ", endTime = " + endTime + "]";
  }

  public EvaluationExamDTO toDto() {
    return new EvaluationExamDTO(identity(), title(), course(), teacher(), startTime, endTime,
        description(), state(), fileContent());
  }

  public Collection<EvaluationExamSection> sections() {
    return this.sections;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof EvaluationExam))
      return false;

    final EvaluationExam that = (EvaluationExam) other;
    if (this == that)
      return true;

    return this.feedbackInfo.equals(that.feedbackInfo) && this.gradeInfo.equals(that.gradeInfo)
        && this.startTime.equals(that.startTime) && this.endTime.equals(that.endTime);
  }
}
