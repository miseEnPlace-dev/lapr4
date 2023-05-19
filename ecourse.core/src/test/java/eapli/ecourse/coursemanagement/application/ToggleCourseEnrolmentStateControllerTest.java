package eapli.ecourse.coursemanagement.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

class ToggleCourseEnrolmentStateControllerTest {

  private ToggleCourseEnrolmentStateController controller;

  private CourseRepository courseRepository;
  private AuthorizationService authz;

  @BeforeEach
  void setUp() {
    courseRepository = mock(CourseRepository.class);
    authz = mock(AuthorizationService.class);
    controller = new ToggleCourseEnrolmentStateController(courseRepository, authz);
  }

  private static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  private SystemUser getNewDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  private Teacher getNewDummyTeacher() {
    return new Teacher(getNewDummyUser(), TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("abc"),
        BirthDate.valueOf(Calendar.getInstance()));
  }

  private Course getDummyCourse() {
    return new Course(CourseCode.valueOf("C01"), CourseTitle.valueOf("Course 01"),
        CourseDescription.valueOf("Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.OPEN),
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED), getNewDummyTeacher());
  }

  private Course getDummyFinishedCourse() {
    return new Course(CourseCode.valueOf("C01"), CourseTitle.valueOf("Course 01"),
        CourseDescription.valueOf("Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.FINISHED),
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED), getNewDummyTeacher());
  }

  private CourseDTO getDummyCourseDTO() {
    return new CourseDTO(CourseCode.valueOf("C01"), CourseTitle.valueOf("Course 01"),
        CourseDescription.valueOf("Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.OPEN),
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED), Calendar.getInstance());
  }

  @Test
  void testListNotClosedCourses() {
    controller.listNotClosedCourses();
    verify(courseRepository, times(1)).findAllNotClosed();
  }

  @Test
  void testToggleEnrolmentState() {
    Course course = getDummyCourse();
    CourseDTO courseDTO = getDummyCourseDTO();

    when(courseRepository.findByCode(courseDTO.getCode())).thenReturn(Optional.of(course));
    when(courseRepository.save(course)).thenReturn(course);

    CourseDTO result = controller.toggleEnrolmentState(courseDTO);

    assertEquals(courseDTO.getCode(), result.getCode());
    assertEquals(courseDTO.getTitle(), result.getTitle());
    assertEquals(courseDTO.getDescription(), result.getDescription());
    assertNotEquals(courseDTO.getEnrolmentState(), result.getEnrolmentState());
    verify(courseRepository, times(1)).findByCode(courseDTO.getCode());
    verify(courseRepository, times(1)).save(course);
  }

  @Test
  void ensureItIsNotPossibleToToggleStateOfFinishedCourse() {
    Course course = getDummyFinishedCourse();
    CourseDTO courseDTO = getDummyCourseDTO();

    when(courseRepository.findByCode(courseDTO.getCode())).thenReturn(Optional.of(course));
    when(courseRepository.save(course)).thenReturn(course);

    assertThrows(IllegalStateException.class, () -> controller.toggleEnrolmentState(courseDTO));
  }
}
