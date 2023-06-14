package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class NumericalQuestion extends Question {
  private static final long serialVersionUID = 1L;

  @Transient
  private final String typeQuestion = "numerical";

  @Column(nullable = false)
  private Double correctAnswer;

  @Column(nullable = false)
  private Double acceptedError;

  public NumericalQuestion(final QuestionBody body, final QuestionType type, double correctAnswer,
      double acceptedError) {
    super(body, type);
    this.correctAnswer = correctAnswer;
    this.acceptedError = acceptedError;
  }

  public NumericalQuestion(final QuestionType type) {
    super(type);
  }

  protected NumericalQuestion() {
    // for ORM only
  }

  public Double correctAnswer() {
    return this.correctAnswer;
  }

  public Double acceptedError() {
    return this.acceptedError;
  }

  public void changeCorrectAnswer(double correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public void changeAcceptedError(double acceptedError) {
    this.acceptedError = acceptedError;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof NumericalQuestion))
      return false;

    final NumericalQuestion that = (NumericalQuestion) other;

    if (this == that)
      return true;

    return this.body().equals(that.body()) && this.type().equals(that.type())
        && this.correctAnswer.equals(that.correctAnswer) && this.acceptedError.equals(that.acceptedError);
  }

  public String typeQuestion() {
    return this.typeQuestion;
  }

  @Override
  public String getQuestionStructure(Question q) {
    NumericalQuestion question = (NumericalQuestion) q;

    StringBuilder sb = new StringBuilder();

    sb.append("@correct-answer" + question.correctAnswer() + ";");
    sb.append("@accepted-error" + question.acceptedError() + ";");

    return sb.toString();
  }
}
