package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.ExamBaseTest;
import eapli.ecourse.questionmanagement.domain.Identifier;

public class EvaluationExamTest extends ExamBaseTest {
  @Test
  public void ensureEvaluationExamHasTitle() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            null, Description.valueOf("Descricao"), new ArrayList<Section>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasDescription() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), null, new ArrayList<Section>(), Time.valueOf(Calendar.getInstance()),
            Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasSections() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), null, Time.valueOf(Calendar.getInstance()),
            Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasOpeningDate() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), new ArrayList<Section>(), null,
            Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasClosingDate() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), new ArrayList<Section>(),
            Time.valueOf(Calendar.getInstance()), null, ExamInfo.NONE, ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasScore() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), new ArrayList<Section>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, null));
  }

  @Test
  public void ensureEvaluationExamHasFeedbackInfo() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), new ArrayList<Section>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), null, ExamInfo.AFTER_CLOSING,
            Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasGradeInfo() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), new ArrayList<Section>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, null,
            Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasCourse() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(null, getNewDummyTeacher(), Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), new ArrayList<Section>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasTeacher() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), null, Identifier.valueOf("Exame"),
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), new ArrayList<Section>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasIdentifier() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), null,
            Title.valueOf("Titulo"), Description.valueOf("Descricao"), new ArrayList<Section>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasTitleAndDescription() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), Identifier.valueOf("Exame"),
            null, null, new ArrayList<Section>(), Time.valueOf(Calendar.getInstance()),
            Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, ExamInfo.AFTER_CLOSING, Score.valueOf(100)));
  }
}
