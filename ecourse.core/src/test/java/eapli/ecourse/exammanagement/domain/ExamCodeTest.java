package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class ExamCodeTest {
  @Test
  public void testExamCodeCreation() {
    String code = "ABC123";
    ExamCode examCode = ExamCode.valueOf(code);
    assertEquals(code, examCode.toString());
  }

  @Test
  public void testExamCodeCreationWithEmptyCode() {
    assertThrows(IllegalArgumentException.class, () -> {
      ExamCode.valueOf("");
    });
  }

  @Test
  public void testExamCodeEquality() {
    String code1 = "ABC123";
    String code2 = "DEF456";
    ExamCode examCode1 = ExamCode.valueOf(code1);
    ExamCode examCode2 = ExamCode.valueOf(code2);
    ExamCode examCode3 = ExamCode.valueOf(code1);
    assertNotEquals(examCode1, examCode2);
    assertEquals(examCode1, examCode3);
  }

  @Test
  public void ensureHashCodeWorks() {
    String code = "ABC123";
    ExamCode examCode = ExamCode.valueOf(code);
    ExamCode examCode2 = ExamCode.valueOf(code);
    assertEquals(examCode2.hashCode(), examCode.hashCode());
  }
}
