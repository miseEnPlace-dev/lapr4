package eapli.ecourse.exammanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.questionmanagement.domain.Feedback;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionCode;
import eapli.ecourse.questionmanagement.domain.QuestionType;

@Entity
public class SectionQuestion {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private QuestionCode code;

  @Column(nullable = false)
  private QuestionBody body;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private QuestionType type;

  @Column(nullable = true)
  private Feedback generalFeedback;

  @ManyToOne
  private Course course;

  public SectionQuestion(Question question) {
    this.code = question.identity();
    this.body = question.body();
    this.type = question.type();
    this.generalFeedback = question.feedback();
    this.course = question.course();
  }

  protected SectionQuestion() {
    // for ORM
  }
}
