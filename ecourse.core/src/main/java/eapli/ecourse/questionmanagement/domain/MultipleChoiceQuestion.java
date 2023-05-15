package eapli.ecourse.questionmanagement.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

@Entity
public class MultipleChoiceQuestion extends Question {
  private static final long serialVersionUID = 1L;

  @ElementCollection
  @CollectionTable(name = "multipleChoiceCorrectAnswer")
  @MapKeyColumn(name = "multipleChoiceCorrectOptionIdentifier")
  @Column(name = "weight")
  private Map<Identifier, Double> correctAnswers;

  @ElementCollection
  @CollectionTable(name = "multipleChoiceOption")
  @MapKeyColumn(name = "multipleOptionIdentifier")
  @Column(name = "multipleChoiceOptionValue")
  private Map<Identifier, String> options;

  @ElementCollection
  @CollectionTable(name = "multipleChoiceOptionFeedback")
  @MapKeyColumn(name = "multipleOptionIdentifier")
  @Column(name = "multipleChoiceFeedbackValue")
  private Map<Identifier, Feedback> feedbacks;

  public MultipleChoiceQuestion(final QuestionBody body, final QuestionType type) {
    super(body, type);
    this.correctAnswers = new HashMap<>();
    this.options = new HashMap<>();
    this.feedbacks = new HashMap<>();
  }

  public MultipleChoiceQuestion(final QuestionType type) {
    super(type);
    this.correctAnswers = new HashMap<>();
    this.options = new HashMap<>();
    this.feedbacks = new HashMap<>();
  }

  protected MultipleChoiceQuestion() {
    // for ORM only
  }

  public void addCorrectAnswer(final Identifier identifier, final Double weight) {
    correctAnswers.put(identifier, weight);
  }

  public void addOption(final Identifier identifier, final String option) {
    options.put(identifier, option);
  }

  public void addFeedback(final Identifier identifier, final Feedback feedback) {
    this.feedbacks.put(identifier, feedback);
  }

  public Map<Identifier, Double> correctAnswers() {
    return this.correctAnswers;
  }

  public Map<Identifier, String> options() {
    return this.options;
  }

  public Map<Identifier, Feedback> feedbacks() {
    return this.feedbacks;
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
