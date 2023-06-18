package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class FeedbackTest {
  @Test
  public void testFeedbackCreation() {
    String feedbackText = "This is a feedback";
    Feedback feedback = Feedback.valueOf(feedbackText);
    assertEquals(feedbackText, feedback.toString());
  }

  @Test
  public void testFeedbackCreationWithEmptyText() {
    assertThrows(IllegalArgumentException.class, () -> {
      Feedback.valueOf("");
    });
  }

  @Test
  public void testFeedbackEquality() {
    String feedbackText1 = "This is a feedback";
    String feedbackText2 = "This is another feedback";
    Feedback feedback1 = Feedback.valueOf(feedbackText1);
    Feedback feedback2 = Feedback.valueOf(feedbackText2);
    Feedback feedback3 = Feedback.valueOf(feedbackText1);
    assertNotEquals(feedback1, feedback2);
    assertEquals(feedback1, feedback3);
  }

  @Test
  public void testHashCode() {
    String feedbackText1 = "This is a feedback";
    String feedbackText2 = "This is another feedback";
    Feedback feedback1 = Feedback.valueOf(feedbackText1);
    Feedback feedback2 = Feedback.valueOf(feedbackText2);
    Feedback feedback3 = Feedback.valueOf(feedbackText1);
    assertNotEquals(feedback1.hashCode(), feedback2.hashCode());
    assertEquals(feedback1.hashCode(), feedback3.hashCode());
  }
}
