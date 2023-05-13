package eapli.ecourse.questionmanagement.domain;

import java.util.Map;
import java.util.Set;

public class MatchingQuestion extends Question {
  private static final long serialVersionUID = 1L;

  private Set<Map<Identifier, Identifier>> options;
  private Set<Map<Identifier, String>> matches;
  private Set<Map<Identifier, String>> correctMatches;
}
