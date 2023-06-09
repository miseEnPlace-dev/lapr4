package eapli.ecourse.answermanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamInfo;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamSection;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class AnswerTest {
  private Answer answer;
  private AnswerId id;
  private Student student;
  private Exam exam;

  private Student dummyStudent(final SystemUser user, final MecanographicNumber mecanographicNumber) {
    return new Student(user, mecanographicNumber);
  }

  private SystemUser getNewDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  private Student getNewDummyStudent() {
    return dummyStudent(getNewDummyUser(), MecanographicNumber.valueOf("1234"));
  }

  private Teacher getDummyTeacher() {
    return new Teacher(getNewDummyUser(), TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("dummy"),
        BirthDate.valueOf(Calendar.getInstance()));
  }

  private SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  private Course getDummyInProgressCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.IN_PROGRESS), new CourseEnrolmentState(), getDummyTeacher());
  }

  private Exam dummyExam() {
    return new EvaluationExam(getDummyInProgressCourse(), getDummyTeacher(),
        ExamIdentifier.valueOf("Exame"),
        ExamTitle.valueOf("Titulo"), ExamDescription.valueOf("Descricao"), null, new ArrayList<EvaluationExamSection>(),
        Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.NONE,
        ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100d));
  }

  @Before
  public void setUp() {
    id = AnswerId.valueOf("123");
    student = getNewDummyStudent();
    exam = dummyExam();
    answer = new Answer(id, student, exam);
  }

  @Test
  public void testIdentity() {
    assertEquals(id, answer.identity());
  }

  @Test
  public void testStudent() {
    assertEquals(student, answer.student());
  }

  @Test
  public void testExam() {
    assertEquals(exam, answer.exam());
  }

  @Test
  public void testSameAs() {
    Answer other = new Answer(id, student, exam);
    assertTrue(answer.sameAs(other));
  }
}
