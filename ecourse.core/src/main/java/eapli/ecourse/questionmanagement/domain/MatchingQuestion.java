package eapli.ecourse.questionmanagement.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MatchingQuestion extends Question {
  private static final long serialVersionUID = 1L;

  @JsonProperty
  private Map<Identifier, Identifier> options;

  @JsonProperty
  private Map<Identifier, String> matches;

  @JsonProperty
  private Map<Identifier, String> correctMatches;

  public MatchingQuestion(final QuestionBody body, QuestionType type) {
    super(body, type);
    options = new HashMap<>();
    matches = new HashMap<>();
    correctMatches = new HashMap<>();
  }

  public void addOption(final Identifier option, final Identifier match) {
    options.put(option, match);
  }

  public void addMatch(final Identifier match, final String description) {
    matches.put(match, description);
  }

  public void addCorrectMatch(final Identifier match, final String description) {
    correctMatches.put(match, description);
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
