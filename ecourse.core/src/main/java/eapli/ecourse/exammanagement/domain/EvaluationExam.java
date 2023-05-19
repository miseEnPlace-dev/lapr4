package eapli.ecourse.exammanagement.domain;

import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.questionmanagement.domain.Identifier;
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

  @Column(nullable = false)
  private Score score;

  public EvaluationExam(Course course, Teacher teacher, Identifier identifier, Title title,
      Description description, Collection<Section> sections, Time startTime, Time endTime,
      ExamInfo feedbackInfo,
      ExamInfo gradeInfo, Score score) {
    super(course, teacher, identifier, title, description, sections);
    Preconditions.noneNull(feedbackInfo, gradeInfo, startTime, endTime, score);
    this.feedbackInfo = feedbackInfo;
    this.gradeInfo = gradeInfo;
    setDates(startTime, endTime);
    this.score = score;
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

  @Override
  public String toString() {
    return super.toString() + "EvaluationExam [endTime=" + endTime + ", feedbackInfo=" + feedbackInfo + ", gradeInfo="
        + gradeInfo
        + ", score=" + score + ", startTime=" + startTime + ", endTime = " + endTime + "]";
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof EvaluationExam))
      return false;

    final EvaluationExam that = (EvaluationExam) other;
    if (this == that)
      return true;

    return this.feedbackInfo.equals(that.feedbackInfo) && this.gradeInfo.equals(that.gradeInfo)
        && this.score.equals(that.score) && this.startTime.equals(that.startTime) && this.endTime.equals(that.endTime);
  }
}
