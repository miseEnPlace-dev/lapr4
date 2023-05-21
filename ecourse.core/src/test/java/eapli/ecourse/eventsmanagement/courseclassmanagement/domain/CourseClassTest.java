package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class CourseClassTest {

  private CourseClass getDummyCourseClass() {
    return new CourseClass(DayInWeek.valueOf(WeekDay.MONDAY), Duration.valueOf(50),
        Hours.valueOf(Calendar.getInstance()), getDummyCourse(), getDummyTeacher());
  }

  private Teacher getDummyTeacher() {
    return new Teacher(getDummyUser(), TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("OMS"),
        BirthDate.valueOf(Calendar.getInstance()));

  }

  private Course getDummyCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(),
        new CourseEnrolmentState(), getDummyTeacher());
  }

  private SystemUser getDummyUser() {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());

    return userBuilder.with("username", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(ClientRoles.STUDENT)
        .build();
  }

  @Test
  public void ensureCourseClassHasDayInWeek() {
    assertEquals(getDummyCourseClass().dayInWeek(), DayInWeek.valueOf(WeekDay.MONDAY));
  }

  @Test
  public void ensureCourseClassHasDuration() {
    assertEquals(getDummyCourseClass().duration(), Duration.valueOf(50));
  }

  @Test
  public void ensureCourseClassHasHours() {
    assertEquals(getDummyCourseClass().hours(), Hours.valueOf(Calendar.getInstance()));
  }

  @Test
  public void ensureCourseClassHasCourse() {
    assertEquals(getDummyCourseClass().course(), getDummyCourse());
  }

  @Test
  public void ensureCourseClassHasTeacher() {
    assertEquals(getDummyCourseClass().scheduledBy(), getDummyTeacher());
  }
}
