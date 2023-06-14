package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MultipleChoiceQuestionTest {
  private MultipleChoiceQuestion question;

  @Before
  public void setUp() {
    question = new MultipleChoiceQuestion(new QuestionBody("What is the capital of France?"),
        QuestionType.FORMATIVE);
  }

  @Test
  public void testAddCorrectAnswer() {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    Double weight = 1.0;
    question.addCorrectAnswer(identifier, weight);
    Map<QuestionIdentifier, Double> expected = new HashMap<>();
    expected.put(identifier, weight);
    assertEquals(expected, question.correctAnswers());
  }

  @Test
  public void testAddOption() {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    String option = "Paris";
    question.addOption(identifier, option);
    Map<QuestionIdentifier, String> expected = new HashMap<>();
    expected.put(identifier, option);
    assertEquals(expected, question.options());
  }

  @Test
  public void testAddFeedback() {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    Feedback feedback = Feedback.valueOf("This is feedback");
    question.addFeedback(identifier, feedback);
    Map<QuestionIdentifier, Feedback> expected = new HashMap<>();
    expected.put(identifier, feedback);
    assertEquals(expected, question.feedbacks());
  }

  @Test
  public void testSameAs() {
    MultipleChoiceQuestion other = new MultipleChoiceQuestion(new QuestionBody("What is the capital of France?"),
        QuestionType.FORMATIVE);
    assertTrue(question.sameAs(other));
  }
}
