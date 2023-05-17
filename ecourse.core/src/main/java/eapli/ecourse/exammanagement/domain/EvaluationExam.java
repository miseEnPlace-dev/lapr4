package eapli.ecourse.exammanagement.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.ecourse.teachermanagement.domain.Teacher;

@Entity
public class EvaluationExam extends Exam {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Temporal(TemporalType.TIMESTAMP)
  private Time startTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Time endTime;

  @Column(nullable = false)
  private ExamInfo feedbackInfo;

  @Column(nullable = false)
  private ExamInfo gradeInfo;

  @Column(nullable = false)
  private Score score;

  public EvaluationExam(Course course, Teacher teacher, Identifier identifier, Title title,
      Description description, ExamState state, Collection<Section> sections, Time startTime, Time endTime,
      ExamInfo feedbackInfo,
      ExamInfo gradeInfo, Score score) {
    super(course, teacher, identifier, title, description, state, sections);
    this.startTime = startTime;
    this.endTime = endTime;
    this.feedbackInfo = feedbackInfo;
    this.gradeInfo = gradeInfo;
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
