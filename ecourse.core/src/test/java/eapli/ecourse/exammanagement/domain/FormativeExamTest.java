package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
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

  private FormativeExam dummyExam() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    ExamScore score = ExamScore.valueOf(12d);
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, score, sections);

    return exam;
  }

  @Before
  public void setUp() {
  }

  @Test
  public void ensureIsPossibleToCreateFormativeExam() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    ExamScore score = ExamScore.valueOf(12d);
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, score, sections);

    assertEquals(course, exam.course());
    assertEquals(identifier, exam.identity());
    assertEquals(title, exam.title());
    assertEquals(description, exam.description());
    assertEquals(sections, exam.sections());
  }

  @Test
  public void ensureFormativeExamIsComparable() {
    exam = dummyExam();

    FormativeExam exam2;

    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("2");
    ExamTitle title = ExamTitle.valueOf("Test Exam Formative");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    ExamScore score = ExamScore.valueOf(12d);
    exam2 = new FormativeExam(course, getDummyTeacher(), identifier, title, description, score, sections);

    assertFalse(exam.equals(exam2));
  }

  @Test
  public void ensureFormativeExamIsNotEqualToNull() {
    exam = dummyExam();

    assertFalse(exam.equals(null));
  }

  @Test
  public void ensureFormativeExamIsEqualToItself() {
    exam = dummyExam();

    assertTrue(exam.equals(exam));
  }

  @Test
  public void ensureFormativeExamIsNotEqualToAnotherType() {
    exam = dummyExam();

    assertFalse(exam.equals("test"));
  }

  @Test
  public void ensureFormativeExamHasSameHashCodeAsItself() {
    exam = dummyExam();

    assertEquals(exam.hashCode(), exam.hashCode());
  }

  @Test
  public void ensureFormativeExamHasDifferentHashCodeThanAnother() {
    exam = dummyExam();

    FormativeExam exam2;

    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("2");
    ExamTitle title = ExamTitle.valueOf("Test Exam Formative");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    ExamScore score = ExamScore.valueOf(12d);
    exam2 = new FormativeExam(course, getDummyTeacher(), identifier, title, description, score, sections);

    assertFalse(exam.hashCode() == exam2.hashCode());
  }

  @Test
  public void ensureFormativeExamSameAsIsWorking() {
    exam = dummyExam();

    FormativeExam exam2 = dummyExam();

    assertTrue(exam.sameAs(exam2));
  }

  @Test
  public void ensureFormativeExamSameAsIsWorkingWithNullFormativeExam() {
    exam = dummyExam();

    assertFalse(exam.sameAs(null));
  }

  @Test
  public void ensureFormativeExamToDtoIsWorking() {
    exam = dummyExam();

    FormativeExamDTO dto = exam.toDto();

    assertEquals(exam.course(), dto.getCourse());
    assertEquals(exam.identity(), dto.getIdentifier());
    assertEquals(exam.title(), dto.getTitle());
    assertEquals(exam.description(), dto.getDescription());
    assertEquals(exam.state(), dto.getState());
    assertEquals(exam.teacher(), dto.getTeacher());
  }
}
