package eapli.ecourse.exammanagement;

import java.util.Calendar;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.exammanagement.domain.ExamSection;
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

public class ExamBaseTest {
  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  public static Course dummyCourse(final CourseCode code, final CourseTitle title, final CourseDescription description,
      final EnrolmentLimits enrolmentLimits, final CourseState courseState, final CourseEnrolmentState enrolmentState,
      final Teacher teacher) {
    return new Course(code, title, description, enrolmentLimits, courseState, enrolmentState, teacher);
  }

  public SystemUser getNewDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  public Course getNewDummyCourse() {
    return dummyCourse(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"), CourseDescription.valueOf("dummy"),
        EnrolmentLimits.valueOf(10, 20), new CourseState(CourseState.State.CLOSED),
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED), getNewDummyTeacher());
  }

  public Teacher getNewDummyTeacher() {
    return new Teacher(getNewDummyUser(), TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("abc"),
        BirthDate.valueOf(Calendar.getInstance()));
  }

  public Course getDummyInProgressCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("scomp"),
        CourseDescription.valueOf("scomp"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.IN_PROGRESS), new CourseEnrolmentState(), getNewDummyTeacher());
  }

  public Course getDummyClosedCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("arqcp"),
        CourseDescription.valueOf("arqcp"), EnrolmentLimits.valueOf(10, 20), new CourseState(CourseState.State.CLOSED),
        new CourseEnrolmentState(), getNewDummyTeacher());
  }
}
