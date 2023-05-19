package eapli.ecourse.coursemanagement.application;

import java.util.Calendar;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class CourseControllerBaseTest {
  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  public SystemUser getNewDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  public Teacher getNewDummyTeacher() {
    return new Teacher(getNewDummyUser(), TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("abc"),
        BirthDate.valueOf(Calendar.getInstance()));
  }

  public Course getDummyCourse() {
    return new Course(CourseCode.valueOf("C01"), CourseTitle.valueOf("Course 01"),
        CourseDescription.valueOf("Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.OPEN),
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED), getNewDummyTeacher());
  }

  public Course getDummyFinishedCourse() {
    return new Course(CourseCode.valueOf("C01"), CourseTitle.valueOf("Course 01"),
        CourseDescription.valueOf("Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.FINISHED),
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED), getNewDummyTeacher());
  }

  public CourseDTO getDummyCourseDTO() {
    return new CourseDTO(CourseCode.valueOf("C01"), CourseTitle.valueOf("Course 01"),
        CourseDescription.valueOf("Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.OPEN),
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED), Calendar.getInstance());
  }

  public TeacherDTO getNewTeacherDTO() {
    return new TeacherDTO(TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("abc"),
        BirthDate.valueOf(Calendar.getInstance()),
        Username.valueOf("usenrma"));
  }
}
