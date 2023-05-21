package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NumericalQuestionTest {
  private NumericalQuestion question;

  @Before
  public void setUp() {
    question = new NumericalQuestion(new QuestionBody("What is the value of pi?"), QuestionType.FORMATIVE, 3.14159,
        0.00001);
  }

  @Test
  public void testChangeCorrectAnswer() {
    double newCorrectAnswer = 3.14;
    question.changeCorrectAnswer(newCorrectAnswer);
    assertEquals(newCorrectAnswer, question.correctAnswer(), 0.001);
  }

  @Test
  public void testChangeAcceptedError() {
    double newAcceptedError = 0.001;
    question.changeAcceptedError(newAcceptedError);
    assertEquals(newAcceptedError, question.acceptedError(), 0.001);
  }

  @Test
  public void testSameAs() {
    NumericalQuestion other = new NumericalQuestion(new QuestionBody("What is the value of pi?"),
        QuestionType.FORMATIVE, 3.14159, 0.00001);
    assertTrue(question.sameAs(other));
  }
}
