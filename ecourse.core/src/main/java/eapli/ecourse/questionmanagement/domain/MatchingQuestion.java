package eapli.ecourse.questionmanagement.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

@Entity
public class MatchingQuestion extends Question {
  private static final long serialVersionUID = 1L;

  @ElementCollection
  @CollectionTable(name = "matchingCorrectMatch")
  @MapKeyColumn(name = "matchingCorrectMatchIdentifier")
  @Column(name = "matchingCorrectOptionIdentifier")
  private Map<String, String> correctMatches; // option identifier, match identifier
  // this is a workaround for the fact that JPA does not support maps with
  // the same key and value type

  @ElementCollection
  @CollectionTable(name = "match")
  @MapKeyColumn(name = "matchIdentifier")
  @Column(name = "matchValue")
  private Map<Identifier, String> matches;

  @ElementCollection
  @CollectionTable(name = "matchingOption")
  @MapKeyColumn(name = "matchingOptionIdentifier")
  @Column(name = "matchingOptionValue")
  private Map<Identifier, String> options;

  @ElementCollection
  @CollectionTable(name = "matchingOptionFeedback")
  @MapKeyColumn(name = "matchingOptionIdentifier")
  @Column(name = "matchingFeedbackValue")
  private Map<Identifier, Feedback> feedback;

  public MatchingQuestion(final QuestionBody body, final QuestionType type) {
    super(body, type);
    this.options = new HashMap<>();
    this.matches = new HashMap<>();
    this.correctMatches = new HashMap<>();
    this.feedback = new HashMap<>();
  }

  public MatchingQuestion(final QuestionType type) {
    super(type);
    this.options = new HashMap<>();
    this.matches = new HashMap<>();
    this.correctMatches = new HashMap<>();
    this.feedback = new HashMap<>();
  }

  protected MatchingQuestion() {
    // for ORM only
  }

  public void addCorrectMatch(final String option, final String match) {
    correctMatches.put(option, match);
  }

  public void addMatch(final Identifier match, final String description) {
    matches.put(match, description);
  }

  public void addOption(final Identifier match, final String description) {
    options.put(match, description);
  }

  public void addFeedback(final Identifier match, final Feedback feedback) {
    this.feedback.put(match, feedback);
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof MatchingQuestion))
      return false;

    final MatchingQuestion that = (MatchingQuestion) other;

    if (this == that)
      return true;

    return this.body().equals(that.body()) && this.type().equals(that.type())
        && this.options.equals(that.options) && this.matches.equals(that.matches)
        && this.correctMatches.equals(that.correctMatches);
  }
}
