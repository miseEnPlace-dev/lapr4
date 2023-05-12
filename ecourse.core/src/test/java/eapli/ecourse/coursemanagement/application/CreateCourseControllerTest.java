package eapli.ecourse.coursemanagement.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class CreateCourseControllerTest {

  private CreateCourseController controller;
  private CourseRepository courseRepository;
  private AuthorizationService authzRegistry;

  @BeforeEach
  public void setup() {
    authzRegistry = mock(AuthorizationService.class);
    courseRepository = mock(CourseRepository.class);
    controller = new CreateCourseController(courseRepository, authzRegistry);
  }

  @Test
  public void ensureItsNotPossibleToCreateCourseWithNullFields() {

    assertThrows(IllegalArgumentException.class, () -> controller.createCourse(null, null, null, 0, 0));
  }

  @Test
  public void ensureItsNotPossibleToCreateCourseWithEmptyFields() {

    assertThrows(IllegalArgumentException.class, () -> controller.createCourse("", "", "", 0, 0));
  }

  @Test
  public void ensureItsNotPossibleToCreateCourseWithNegativeLimits() {

    assertThrows(IllegalArgumentException.class, () -> controller.createCourse("1234", "dummy", "dummy", -1, -1));
  }

  @Test
  public void ensureItsPossibleToCreateCourses() {

    Course course = controller.createCourse("1234", "dummy", "dummy", 10, 20);

    assertEquals(CourseCode.valueOf("1234"), course.code());
    assertEquals(CourseTitle.valueOf("dummy"), course.title());
    assertEquals(CourseDescription.valueOf("dummy"), course.description());
    assertEquals(EnrolmentLimits.valueOf(10, 20), course.enrolmentLimits());
    assertEquals(new CourseState(), course.state());
    assertEquals(new CourseEnrolmentState(), course.enrolmentState());
  }

  @Test
  public void ensureCheckDuplicatesWork() {

      when(courseRepository.containsOfIdentity(CourseCode.valueOf("1234"))).thenReturn(true);

      assertThrows(IllegalStateException.class, () -> controller.createCourse("1234", "dummy", "dummy", 10, 20));
  }

}
