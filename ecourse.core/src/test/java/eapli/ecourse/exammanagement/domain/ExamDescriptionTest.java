package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class ExamDescriptionTest {

  @Test
  public void testExamDescriptionCreation() {
    String description = "This is a section description.";
    ExamDescription examDescription = ExamDescription.valueOf(description);
    assertEquals(description, examDescription.toString());
  }

  @Test
  public void testExamDescriptionCreationWithLongDescription() {
    String longDescription = "This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters.";
    assertThrows(IllegalArgumentException.class, () -> {
      ExamDescription.valueOf(longDescription);
    });
  }

  @Test
  public void testExamDescriptionEquality() {
    String description1 = "This is a section description.";
    String description2 = "This is another section description.";
    ExamDescription examDescription1 = ExamDescription.valueOf(description1);
    ExamDescription examDescription2 = ExamDescription.valueOf(description2);
    ExamDescription examDescription3 = ExamDescription.valueOf(description1);
    assertNotEquals(examDescription1, examDescription2);
    assertEquals(examDescription1, examDescription3);
  }

  @Test
  public void ensureHashCodeIsWorking() {
    String description1 = "This is a section description.";
    String description2 = "This is another section description.";
    ExamDescription examDescription1 = ExamDescription.valueOf(description1);
    ExamDescription examDescription2 = ExamDescription.valueOf(description2);
    ExamDescription examDescription3 = ExamDescription.valueOf(description1);
    assertNotEquals(examDescription1.hashCode(), examDescription2.hashCode());
    assertEquals(examDescription1.hashCode(), examDescription3.hashCode());
  }
}
