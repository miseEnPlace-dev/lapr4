package eapli.ecourse.questionmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import eapli.ecourse.questionmanagement.domain.QuestionCode;

public class QuestionCodeTest {
  @Test
  public void testQuestionCodeCreation() {
    String code = "QST001";
    QuestionCode questionCode = QuestionCode.valueOf(code);
    assertEquals(code, questionCode.toString());
  }

  @Test
  public void testQuestionCodeCreationWithEmptyCode() {
    assertThrows(IllegalArgumentException.class, () -> {
      QuestionCode.valueOf("");
    });
  }

  @Test
  public void testQuestionCodeNewID() {
    QuestionCode questionCode = QuestionCode.newID();
    assertNotNull(questionCode);
    QuestionCode questionCode2 = QuestionCode.newID();
    assertNotEquals(questionCode, questionCode2);
  }

  @Test
  public void testQuestionCodeEquality() {
    String code1 = "QST001";
    String code2 = "QST002";
    QuestionCode questionCode1 = QuestionCode.valueOf(code1);
    QuestionCode questionCode2 = QuestionCode.valueOf(code2);
    QuestionCode questionCode3 = QuestionCode.valueOf(code1);
    assertNotEquals(questionCode1, questionCode2);
    assertEquals(questionCode1, questionCode3);
  }
}
