package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.Calendar;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.DayInWeek;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.Hours;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.WeekDay;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.infrastructure.bootstrapers.CourseBootstrapperBase;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class CourseClassBootstrapper extends CourseBootstrapperBase implements Action {

  @Override
  public boolean execute() {

    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    SystemUser user = userBuilder.with("username", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(ClientRoles.STUDENT)
        .build();

    Teacher teacher = new Teacher(user, TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("OMS"),
        BirthDate.valueOf(Calendar.getInstance()));

    Course course = createOpenCourse("123456789", "test", "test", 0, 100, teacher.toDto());

    try {
      new CourseClass(DayInWeek.valueOf(WeekDay.MONDAY), Duration.valueOf(50),
          Hours.valueOf(Calendar.getInstance()), course, teacher);
    } catch (final Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }

    return true;
  }

}
