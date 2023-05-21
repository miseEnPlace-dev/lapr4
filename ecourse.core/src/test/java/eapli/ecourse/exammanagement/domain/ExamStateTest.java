package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExamStateTest {
  @Test
  public void testExamStateDefaultCreation() {
    ExamState examState = new ExamState();
    assertTrue(examState.isDraft());
  }

  @Test
  public void testExamStateChangeToDraft() {
    ExamState examState = new ExamState(ExamState.State.PUBLISHED);
    examState.changeToDraft();
    assertTrue(examState.isDraft());
  }

  @Test
  public void testExamStateChangeToPublished() {
    ExamState examState = new ExamState(ExamState.State.DRAFT);
    examState.changeToPublished();
    assertTrue(examState.isPublished());
  }

  @Test
  public void testExamStateIsSameAs() {
    ExamState examState1 = new ExamState(ExamState.State.DRAFT);
    assertTrue(examState1.isSameAs(ExamState.State.DRAFT));
  }

  @Test
  public void testExamStateIsNotSameAs() {
    ExamState examState1 = new ExamState(ExamState.State.DRAFT);
    assertFalse(examState1.isSameAs(ExamState.State.PUBLISHED));
  }

  @Test
  public void testExamStateEquality() {
    ExamState examState1 = new ExamState(ExamState.State.DRAFT);
    ExamState examState2 = new ExamState(ExamState.State.DRAFT);
    ExamState examState3 = new ExamState(ExamState.State.PUBLISHED);
    assertEquals(examState1, examState2);
    assertNotEquals(examState1, examState3);
  }
}
