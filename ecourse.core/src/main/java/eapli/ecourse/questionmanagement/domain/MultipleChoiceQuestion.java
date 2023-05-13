package eapli.ecourse.questionmanagement.domain;

import java.util.HashMap;
import java.util.Map;

public class MultipleChoiceQuestion extends Question {
  private static final long serialVersionUID = 1L;

  private Map<Identifier, Double> correctAnswers;
  private Map<Identifier, String> options;

  public MultipleChoiceQuestion(final QuestionBody body, QuestionType type) {
    super(body, type);
    this.correctAnswers = new HashMap<>();
    this.options = new HashMap<>();
  }

  public void addCorrectAnswer(final Identifier identifier, final Double correctAnswer) {
    correctAnswers.put(identifier, correctAnswer);
  }

  public void addOption(final Identifier identifier, final String option) {
    options.put(identifier, option);
  }

  public Map<Identifier, Double> correctAnswers() {
    return this.correctAnswers;
  }

  public Map<Identifier, String> options() {
    return this.options;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof MultipleChoiceQuestion))
      return false;

    final MultipleChoiceQuestion that = (MultipleChoiceQuestion) other;

    if (this == that)
      return true;

    return this.body().equals(that.body()) && this.type().equals(that.type())
        && this.correctAnswers.equals(that.correctAnswers) && this.options.equals(that.options);
  }
}
