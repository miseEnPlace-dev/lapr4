package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.ExamBaseTest;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamSection;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;

public class EvaluationExamTest extends ExamBaseTest {
  @Test
  public void ensureEvaluationExamHasTitle() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            null, ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasDescription() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), null, new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()),
            Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasSections() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), null,
            Time.valueOf(Calendar.getInstance()),
            Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasOpeningDate() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            null,
            Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasClosingDate() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()), null, ExamInfo.NONE, ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasScore() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, null));
  }

  @Test
  public void ensureEvaluationExamHasFeedbackInfo() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), null, ExamInfo.AFTER_CLOSING,
            ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasGradeInfo() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, null,
            ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasCourse() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(null, getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasTeacher() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), null, ExamIdentifier.valueOf("Exame"),
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasIdentifier() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), null,
            ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), new ArrayList<EvaluationExamSection>(),
            Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
            ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }

  @Test
  public void ensureEvaluationExamHasTitleAndDescription() {
    assertThrows(IllegalArgumentException.class,
        () -> new EvaluationExam(getDummyInProgressCourse(), getNewDummyTeacher(), ExamIdentifier.valueOf("Exame"),
            null, null, new ArrayList<EvaluationExamSection>(), Time.valueOf(Calendar.getInstance()),
            Time.valueOf(Calendar.getInstance()), ExamInfo.NONE, ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100)));
  }
}
