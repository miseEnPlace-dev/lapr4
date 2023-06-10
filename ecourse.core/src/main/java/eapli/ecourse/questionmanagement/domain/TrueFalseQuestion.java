package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TrueFalseQuestion extends Question {
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private boolean correctAnswer;

  public TrueFalseQuestion(final QuestionBody body, final QuestionType type, boolean correctAnswer) {
    super(body, type);
    this.correctAnswer = correctAnswer;
  }

  public TrueFalseQuestion(final QuestionType type) {
    super(type);
  }

  protected TrueFalseQuestion() {
    // for ORM only
  }

  public void changeCorrectAnswer(boolean correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public boolean correctAnswer() {
    return this.correctAnswer;
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

  @Override
  public String getQuestionStructure(Question q) {
    TrueFalseQuestion question = (TrueFalseQuestion) q;

    StringBuilder sb = new StringBuilder();

    sb.append("@correct-answer " + question.correctAnswer() + ";");

    return sb.toString();
  }
}
