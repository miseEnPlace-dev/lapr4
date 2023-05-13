package eapli.ecourse.questionmanagement.domain;

public class NumericalQuestion extends Question {
  private static final long serialVersionUID = 1L;

  private Double correctAnswer;
  private Double acceptedError;

  public NumericalQuestion(final QuestionBody body, QuestionType type, double correctAnswer, double acceptedError) {
    super(body, type);
    this.correctAnswer = correctAnswer;
    this.acceptedError = acceptedError;
  }

  public Double correctAnswer() {
    return this.correctAnswer;
  }

  public Double acceptedError() {
    return this.acceptedError;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof NumericalQuestion))
      return false;

    final NumericalQuestion that = (NumericalQuestion) other;

    if (this == that)
      return true;

    return this.body().equals(that.body()) && this.type().equals(that.type())
        && this.correctAnswer == that.correctAnswer && this.acceptedError == that.acceptedError;
  }
}
