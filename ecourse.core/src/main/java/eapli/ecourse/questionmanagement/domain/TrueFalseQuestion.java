package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TrueFalseQuestion extends Question {
  private static final long serialVersionUID = 1L;

  @Column
  private boolean correctAnswer;

  public TrueFalseQuestion(final QuestionBody body, QuestionType type, boolean correctAnswer) {
    super(body, type);
    this.correctAnswer = correctAnswer;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof TrueFalseQuestion))
      return false;

    final TrueFalseQuestion that = (TrueFalseQuestion) other;

    if (this == that)
      return true;

    return this.body().equals(that.body()) && this.type().equals(that.type())
        && this.correctAnswer == that.correctAnswer;
  }
}
