package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExamIdentifierTest {
  @Test
  public void testExamIdentifierCreation() {
    String identifier = "Exam 1";
    ExamIdentifier examIdentifier = ExamIdentifier.valueOf(identifier);
    assertEquals(identifier, examIdentifier.toString());
  }

  @Test
  public void testExamIdentifierCreationWithEmptyIdentifier() {
    assertThrows(IllegalArgumentException.class, () -> {
      ExamIdentifier.valueOf("");
    });
  }

  @Test
  public void testExamIdentifierCreationWithLongIdentifier() {
    String longIdentifier = "This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters.";
    assertThrows(IllegalArgumentException.class, () -> {
      ExamIdentifier.valueOf(longIdentifier);
    });
  }

  @Test
  public void testExamIdentifierEquality() {
    String identifier1 = "Exam 1";
    String identifier2 = "Exam 2";
    ExamIdentifier examIdentifier1 = ExamIdentifier.valueOf(identifier1);
    ExamIdentifier examIdentifier2 = ExamIdentifier.valueOf(identifier2);
    ExamIdentifier examIdentifier3 = ExamIdentifier.valueOf(identifier1);
    assertNotEquals(examIdentifier1, examIdentifier2);
    assertEquals(examIdentifier1, examIdentifier3);
  }

  @Test
  public void ensureHashCodeIsCorrect() {
    String identifier = "Exam 1";
    ExamIdentifier examIdentifier = ExamIdentifier.valueOf(identifier);
    ExamIdentifier examIdentifier2 = ExamIdentifier.valueOf(identifier);
    assertEquals(examIdentifier2.hashCode(), examIdentifier.hashCode());
  }

  @Test
  public void ensureCompareToIsWorking() {
    String identifier1 = "Exam 1";
    String identifier2 = "Exam 2";
    ExamIdentifier examIdentifier1 = ExamIdentifier.valueOf(identifier1);
    ExamIdentifier examIdentifier2 = ExamIdentifier.valueOf(identifier2);
    assertTrue(examIdentifier1.compareTo(examIdentifier2) < 0);
    assertTrue(examIdentifier2.compareTo(examIdentifier1) > 0);
    assertEquals(0, examIdentifier1.compareTo(examIdentifier1));
  }

  @Test
  public void ensureToStringReturnsTheIdentifier() {
    String identifier = "Exam 1";
    ExamIdentifier examIdentifier = ExamIdentifier.valueOf(identifier);
    assertEquals(identifier, examIdentifier.toString());
  }
}
