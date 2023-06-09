package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.framework.domain.model.AggregateRoot;

@Entity
@DiscriminatorColumn(name = "disc", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question implements AggregateRoot<QuestionCode> {
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
  private ExamScore score;

  @Column(nullable = true)
  private Feedback generalFeedback;

  @Column(name = "disc", insertable = false, updatable = false)
  protected String disc;

  @ManyToOne
  private Course course;

  public Question(QuestionBody body, QuestionType type, Feedback generalFeedback, Course course) {
    this.code = QuestionCode.newID();
    this.body = body;
    this.type = type;
    this.generalFeedback = generalFeedback;
    this.course = course;
    this.score = null;
  }

  public Question(QuestionType type) {
    this.code = QuestionCode.newID();
    this.type = type;
    this.score = null;
  }

  public Question(QuestionBody body, QuestionType type) {
    this.code = QuestionCode.newID();
    this.body = body;
    this.type = type;
    this.score = null;
  }

  protected Question() {
    // for ORM
  }

  public void changeBody(QuestionBody body) {
    this.body = body;
  }

  public void changeFeedback(Feedback feedback) {
    this.generalFeedback = feedback;
  }

  public void changeCourse(Course course) {
    this.course = course;
  }

  public void changeScore(ExamScore score) {
    this.score = score;
  }

  public QuestionBody body() {
    return this.body;
  }

  public QuestionType type() {
    return this.type;
  }

  public Feedback feedback() {
    return this.generalFeedback;
  }

  public Course course() {
    return this.course;
  }

  public ExamScore score() {
    return this.score;
  }

  @Override
  public QuestionCode identity() {
    return this.code;
  }

  public abstract boolean sameAs(Object other);
}
