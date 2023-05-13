package eapli.ecourse.questionmanagement.domain;

import java.util.HashMap;
import java.util.Map;

public class ShortAnswerQuestion extends Question {
  private static final long serialVersionUID = 1L;

  private Map<String, Double> correctAnswers;

  public ShortAnswerQuestion(final QuestionBody body, QuestionType type) {
    super(body, type);
    this.correctAnswers = new HashMap<>();
  }

  public void addCorrectAnswer(final String correctAnswer, final Double grade) {
    correctAnswers.put(correctAnswer, grade);
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof ShortAnswerQuestion))
      return false;

    final ShortAnswerQuestion that = (ShortAnswerQuestion) other;

    if (this == that)
      return true;

    return this.body().equals(that.body()) && this.type().equals(that.type())
        && this.correctAnswers.equals(that.correctAnswers);
  }
}
