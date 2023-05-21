package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class ExtraordinaryClassTest {

  private Teacher teacher;
  private Course course;
  private Set<Student> students;

  @Before
  public void setUp() {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    SystemUser user = userBuilder.with("username", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(ClientRoles.TEACHER)
        .build();

    teacher = new Teacher(user, TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("OMS"),
        BirthDate.valueOf(Calendar.getInstance()));

    final SystemUserBuilder userBuilder2 = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    SystemUser user2 = userBuilder2.with("username", "duMMy1", "dummy", "dummy", "a@b.ro")
        .withRoles(ClientRoles.STUDENT)
        .build();

    Student student = new Student(user2, MecanographicNumber.valueOf("stilton"));

    students = new HashSet<>();
    students.add(student);

    course = new Course(CourseCode.valueOf("code"), CourseTitle.valueOf("Title"),
        CourseDescription.valueOf("description"), EnrolmentLimits.valueOf(1, 2), teacher);
  }

  @Test
  public void ensureExtraordinaryClassIsCreated() {
    ExtraordinaryClass ec = new ExtraordinaryClass(
        Duration.valueOf(30), Time.valueOf(Calendar.getInstance()), teacher, students, course);

    assertTrue(ec != null);
  }

  @Test
  public void ensureExtraordinaryClassHasDuration() {
    assertThrows(IllegalArgumentException.class, () -> new ExtraordinaryClass(
        null, Time.valueOf(Calendar.getInstance()), teacher, students, course));
  }

  @Test
  public void ensureExtraordinaryClassHasTime() {
    assertThrows(IllegalArgumentException.class, () -> new ExtraordinaryClass(
        Duration.valueOf(30), null, teacher, students, course));
  }

  @Test
  public void ensureExtraordinaryClassHasTeacher() {
    assertThrows(IllegalArgumentException.class, () -> new ExtraordinaryClass(
        Duration.valueOf(30), Time.valueOf(Calendar.getInstance()), null, students, course));
  }

  @Test
  public void ensureExtraordinaryClassHasStudents() {
    assertThrows(IllegalArgumentException.class, () -> new ExtraordinaryClass(
        Duration.valueOf(30), Time.valueOf(Calendar.getInstance()), teacher, null, course));
  }

  @Test
  public void ensureExtraordinaryClassHasCourse() {
    assertThrows(IllegalArgumentException.class, () -> new ExtraordinaryClass(
        Duration.valueOf(30), Time.valueOf(Calendar.getInstance()), teacher, students, null));
  }

  @Test
  public void ensureSameAsWorks() {
    ExtraordinaryClass ec = new ExtraordinaryClass(
        Duration.valueOf(30), Time.valueOf(Calendar.getInstance()), teacher, students, course);

    ExtraordinaryClass ec2 = new ExtraordinaryClass(
        Duration.valueOf(40), Time.valueOf(Calendar.getInstance()), teacher, students, course);

    assertTrue(ec.sameAs(ec));
    assertTrue(!ec.sameAs(ec2));
    assertTrue(!ec.sameAs(null));
    assertTrue(!ec.sameAs(new Object()));
  }

  @Test
  public void ensureExtraordinaryClassHasId() {
    ExtraordinaryClass ec = new ExtraordinaryClass(
        Duration.valueOf(30), Time.valueOf(Calendar.getInstance()), teacher, students, course);

    assertTrue(ec.identity() != null);
  }

}
