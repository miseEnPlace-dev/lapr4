package eapli.ecourse.exammanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.framework.domain.model.AggregateRoot;

@Entity
public class EvaluationExam extends Exam {
  private static final long serialVersionUID = 1L;

  @Temporal(TemporalType.TIMESTAMP)
  Time startTime;

  @Temporal(TemporalType.TIMESTAMP)
  Time endTime;

  @Column(nullable = false)
  ExamInfo feedbackInfo;

  @Column(nullable = false)
  ExamInfo gradeInfo;

  @Column(nullable = false)
  Score score;

  public EvaluationExam() {
    super();
    this.startTime = null;
    this.endTime = null;
    this.feedbackInfo = null;
    this.gradeInfo = null;
    this.score = null;
  }

  public void changeStartTime(Time startTime) {
    this.startTime = startTime;
  }

  public void changeEndTime(Time endTime) {
    this.endTime = endTime;
  }

  public void changeFeedbackInfo(ExamInfo feedbackInfo) {
    this.feedbackInfo = feedbackInfo;
  }

  public void changeGradeInfo(ExamInfo gradeInfo) {
    this.gradeInfo = gradeInfo;
  }

  public void changeScore(Score score) {
    this.score = score;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof EvaluationExam)) {
      return false;
    }

    final EvaluationExam that = (EvaluationExam) other;
    if (this == that) {
      return true;
    }

    return identity().equals(that.identity());
  }
}
