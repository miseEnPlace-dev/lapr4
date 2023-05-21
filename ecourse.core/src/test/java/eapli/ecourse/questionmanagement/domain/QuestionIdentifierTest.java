package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class QuestionIdentifierTest {

  @Test
  public void testQuestionIdentifierCreation() {
    String identifier = "QST001";
    QuestionIdentifier questionIdentifier = QuestionIdentifier.valueOf(identifier);
    assertEquals(identifier, questionIdentifier.toString());
  }

  @Test
  public void testQuestionIdentifierCreationWithEmptyIdentifier() {
    assertThrows(IllegalArgumentException.class, () -> {
      QuestionIdentifier.valueOf("");
    });
  }

  @Test
  public void testQuestionIdentifierCreationWithLongIdentifier() {
    String identifier = "QST001".repeat(50);
    assertThrows(IllegalArgumentException.class, () -> {
      QuestionIdentifier.valueOf(identifier);
    });
  }

  @Test
  public void testQuestionIdentifierEquality() {
    String identifier1 = "QST001";
    String identifier2 = "QST002";
    QuestionIdentifier questionIdentifier1 = QuestionIdentifier.valueOf(identifier1);
    QuestionIdentifier questionIdentifier2 = QuestionIdentifier.valueOf(identifier2);
    QuestionIdentifier questionIdentifier3 = QuestionIdentifier.valueOf(identifier1);
    assertNotEquals(questionIdentifier1, questionIdentifier2);
    assertEquals(questionIdentifier1, questionIdentifier3);
  }

  @Test
  public void ensureHashCodeWorks() {
    String identifier = "QST001";
    QuestionIdentifier questionIdentifier = QuestionIdentifier.valueOf(identifier);
    assertEquals(QuestionIdentifier.valueOf(identifier).hashCode(), questionIdentifier.hashCode());
  }
}
