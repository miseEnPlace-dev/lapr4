package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class QuestionBodyTest {
  @Test
  public void testQuestionBodyCreation() {
    String body = "What is the capital of France?";
    QuestionBody questionBody = QuestionBody.valueOf(body);
    assertEquals(body, questionBody.toString());
  }

  @Test
  public void testQuestionBodyCreationWithEmptyBody() {
    assertThrows(IllegalArgumentException.class, () -> {
      QuestionBody.valueOf("");
    });
  }

  @Test
  public void testQuestionBodyEquality() {
    String body1 = "What is the capital of France?";
    String body2 = "What is the capital of Spain?";
    QuestionBody questionBody1 = QuestionBody.valueOf(body1);
    QuestionBody questionBody2 = QuestionBody.valueOf(body2);
    QuestionBody questionBody3 = QuestionBody.valueOf(body1);
    assertNotEquals(questionBody1, questionBody2);
    assertEquals(questionBody1, questionBody3);
  }
}
