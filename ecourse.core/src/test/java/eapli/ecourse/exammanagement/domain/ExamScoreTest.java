package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;

public class ExamScoreTest {

  @Test
  public void testExamScoreCreation() {
    Double score = 10d;
    ExamScore examScore = ExamScore.valueOf(score);
    assertTrue(examScore.compareTo(ExamScore.valueOf(score)) == 0);
  }

  @Test
  public void testExamScoreEquality() {
    Double score1 = 10d;
    Double score2 = 20d;
    ExamScore examScore1 = ExamScore.valueOf(score1);
    ExamScore examScore2 = ExamScore.valueOf(score2);
    ExamScore examScore3 = ExamScore.valueOf(score1);
    assertNotEquals(examScore1, examScore2);
    assertEquals(examScore1, examScore3);
  }

  @Test
  public void ensureHashCodeWorks() {
    Double score1 = 10d;
    Double score2 = 20d;
    ExamScore examScore1 = ExamScore.valueOf(score1);
    ExamScore examScore2 = ExamScore.valueOf(score2);
    ExamScore examScore3 = ExamScore.valueOf(score1);
    assertNotEquals(examScore1.hashCode(), examScore2.hashCode());
    assertEquals(examScore1.hashCode(), examScore3.hashCode());
  }
}
