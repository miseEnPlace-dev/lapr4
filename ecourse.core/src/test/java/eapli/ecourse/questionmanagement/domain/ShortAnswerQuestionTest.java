package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ShortAnswerQuestionTest {
  private ShortAnswerQuestion question;

  @Before
  public void setUp() {
    question = new ShortAnswerQuestion(new QuestionBody("What is the capital of Portugal?"), QuestionType.FORMATIVE);
  }

  @Test
  public void testAddCorrectAnswer() {
    String correctAnswer = "Lisbon";
    Double grade = 1.0;
    question.addCorrectAnswer(correctAnswer, grade);
    Map<String, Double> expected = new HashMap<>();
    expected.put(correctAnswer, grade);
    assertEquals(expected, question.correctAnswers());
  }

  @Test
  public void testSameAs() {
    ShortAnswerQuestion other = new ShortAnswerQuestion(new QuestionBody("What is the capital of Portugal?"),
        QuestionType.FORMATIVE);
    assertTrue(question.sameAs(other));
  }

  @Test
  public void ensureSameAsIsFalseWithDifferentQuestionBody() {
    ShortAnswerQuestion other = new ShortAnswerQuestion(new QuestionBody("What is the capital of Spain?"),
        QuestionType.FORMATIVE);
    assertFalse(question.sameAs(other));
  }

  @Test
  public void ensureSameAsIsFalseWithDifferentQuestionType() {
    ShortAnswerQuestion other = new ShortAnswerQuestion(new QuestionBody("What is the capital of Portugal?"),
        QuestionType.REGULAR);
    assertFalse(question.sameAs(other));
  }
}
