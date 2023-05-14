package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

import eapli.framework.domain.model.AggregateRoot;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

  @Column
  private Feedback generalFeedback;

  public Question(QuestionBody body, QuestionType type, Feedback generalFeedback) {
    this.code = QuestionCode.newID();
    this.body = body;
    this.type = type;
    this.generalFeedback = generalFeedback;
  }

  public Question(QuestionBody body, QuestionType type) {
    this.code = QuestionCode.newID();
    this.body = body;
    this.type = type;
  }

  protected Question() {
    // for ORM
  }

  public QuestionBody body() {
    return this.body;
  }

  public QuestionType type() {
    return this.type;
  }

  @Override
  public QuestionCode identity() {
    return this.code;
  }

  public abstract boolean sameAs(Object other);
}
