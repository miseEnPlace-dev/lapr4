package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MatchingQuestionTest {

  private MatchingQuestion question;

  @Before
  public void setUp() {
    question = new MatchingQuestion(new QuestionBody("Match the following countries with their capitals:"),
        QuestionType.FORMATIVE);
  }

  @Test
  public void testAddCorrectMatch() {
    String option = "A";
    String match = "1";
    question.addCorrectMatch(option, match);
    Map<String, String> expected = new HashMap<>();
    expected.put(option, match);
    assertEquals(expected, question.correctMatches());
  }

  @Test
  public void testAddMatch() {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    String match = "Paris";
    question.addMatch(identifier, match);
    Map<QuestionIdentifier, String> expected = new HashMap<>();
    expected.put(identifier, match);
    assertEquals(expected, question.matches());
  }

  @Test
  public void testAddOption() {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("A");
    String option = "France";
    question.addOption(identifier, option);
    Map<QuestionIdentifier, String> expected = new HashMap<>();
    expected.put(identifier, option);
    assertEquals(expected, question.options());
  }

  @Test
  public void testAddFeedback() {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("A");
    Feedback feedback = Feedback.valueOf("This is feedback");
    question.addFeedback(identifier, feedback);
    Map<QuestionIdentifier, Feedback> expected = new HashMap<>();
    expected.put(identifier, feedback);
    assertEquals(expected, question.feedbacks());
  }

  @Test
  public void testSameAs() {
    MatchingQuestion other = new MatchingQuestion(
        new QuestionBody("Match the following countries with their capitals:"), QuestionType.FORMATIVE);
    assertTrue(question.sameAs(other));
  }
}
