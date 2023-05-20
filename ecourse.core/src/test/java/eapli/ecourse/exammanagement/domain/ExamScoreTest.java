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
    int score = 10;
    ExamScore examScore = ExamScore.valueOf(score);
    assertTrue(examScore.compareTo(ExamScore.valueOf(score)) == 0);
  }

  @Test
  public void testExamScoreCreationWithNegativeScore() {
    assertThrows(IllegalArgumentException.class, () -> {
      ExamScore.valueOf(-10);
    });
  }

  @Test
  public void testExamScoreEquality() {
    int score1 = 10;
    int score2 = 20;
    ExamScore examScore1 = ExamScore.valueOf(score1);
    ExamScore examScore2 = ExamScore.valueOf(score2);
    ExamScore examScore3 = ExamScore.valueOf(score1);
    assertNotEquals(examScore1, examScore2);
    assertEquals(examScore1, examScore3);
  }

  @Test
  public void ensureHashCodeWorks() {
    int score1 = 10;
    int score2 = 20;
    ExamScore examScore1 = ExamScore.valueOf(score1);
    ExamScore examScore2 = ExamScore.valueOf(score2);
    ExamScore examScore3 = ExamScore.valueOf(score1);
    assertNotEquals(examScore1.hashCode(), examScore2.hashCode());
    assertEquals(examScore1.hashCode(), examScore3.hashCode());
  }
}
