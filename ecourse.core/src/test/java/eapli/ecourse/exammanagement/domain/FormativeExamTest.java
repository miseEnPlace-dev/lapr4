package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
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

public class FormativeExamTest {

  private FormativeExam exam;

  public Course getDummyCourse() {
    return new Course(CourseCode.valueOf("id"), CourseTitle.valueOf("Test Course"),
        CourseDescription.valueOf("Test Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.CLOSED), new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED),
        null);
  }

  private Teacher getDummyTeacher() {
    return new Teacher(getDummyUser(), TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("dummy"),
        BirthDate.valueOf(Calendar.getInstance()));
  }

  private SystemUser getDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  @Before
  public void setUp() {
  }

  @Test
  public void ensureIsPossbileToCreateFormativeExam() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    ExamScore score = ExamScore.valueOf(12d);
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, score, sections);

    assertEquals(course, exam.course());
    assertEquals(identifier, exam.identifier());
    assertEquals(title, exam.title());
    assertEquals(description, exam.description());
    assertEquals(sections, exam.sections());
  }
}
