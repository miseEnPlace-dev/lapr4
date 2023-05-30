package eapli.ecourse.answermanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import eapli.ecourse.questionmanagement.domain.Question;

@Entity
public class AnswerToQuestion {
  @Column(nullable = false)
  private Answer answer;

  @ManyToOne
  private Question question;

  @Column
  private Score score;

  protected AnswerToQuestion() {
    // for ORM
  }

  public AnswerToQuestion(Answer answer, Question question) {
    this.answer = answer;
    this.question = question;
  }

  public Answer answer() {
    return answer;
  }

  public Question question() {
    return question;
  }

  public Score score() {
    return score;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof AnswerToQuestion))
      return false;

    final AnswerToQuestion that = (AnswerToQuestion) other;
    if (this == that)
      return true;

    return answer.equals(that.answer) && question.equals(that.question);
  }
}
