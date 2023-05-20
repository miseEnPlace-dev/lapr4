package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class ExamTitleTest {
  @Test
  public void testExamTitleCreation() {
    String title = "Exam Title";
    ExamTitle examTitle = ExamTitle.valueOf(title);
    assertEquals(title, examTitle.toString());
  }

  @Test
  public void testExamTitleCreationWithEmptyTitle() {
    assertThrows(IllegalArgumentException.class, () -> {
      ExamTitle.valueOf("");
    });
  }

  @Test
  public void testExamTitleCreationWithLongTitle() {
    String title = "Exam Title".repeat(50);
    assertThrows(IllegalArgumentException.class, () -> {
      ExamTitle.valueOf(title);
    });
  }

  @Test
  public void testExamTitleEquality() {
    String title1 = "Exam Title";
    String title2 = "Another Exam Title";
    ExamTitle examTitle1 = ExamTitle.valueOf(title1);
    ExamTitle examTitle2 = ExamTitle.valueOf(title2);
    ExamTitle examTitle3 = ExamTitle.valueOf(title1);
    assertNotEquals(examTitle1, examTitle2);
    assertEquals(examTitle1, examTitle3);
  }
}
