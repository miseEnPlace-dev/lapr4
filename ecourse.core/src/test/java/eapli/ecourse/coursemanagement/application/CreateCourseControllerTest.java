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

public class CreateCourseControllerTest {

  private CreateCourseController controller;
  private CourseRepository courseRepository;

  @BeforeEach
  public void setup() {
    courseRepository = mock(CourseRepository.class);
    controller = new CreateCourseController(courseRepository);
  }

  @Test
  public void ensureItsNotPossibleToCreateCourseWithNullFields() {

    assertThrows(IllegalArgumentException.class, () -> controller.createCourse(null, null, null, null, null, null));
  }

  @Test
  public void ensureItsPossibleToCreateCourse() {
    when(courseRepository.containsOfIdentity(CourseCode.valueOf("1234"))).thenReturn(false);

    Course course = controller.createCourse(CourseCode.valueOf("1234"),
        CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(),
        new CourseEnrolmentState());

    assertEquals(CourseCode.valueOf("1234"), course.code());
    assertEquals(CourseTitle.valueOf("dummy"), course.title());
    assertEquals(CourseDescription.valueOf("dummy"), course.description());
    assertEquals(EnrolmentLimits.valueOf(10, 20), course.enrolmentLimits());
    assertEquals(new CourseState(), course.state());
    assertEquals(new CourseEnrolmentState(), course.enrolmentState());
  }

  @Test
  public void ensureItsPossibleToCreateCourseWithoutStates() {

    Course course = controller.createCourse(CourseCode.valueOf("1234"),
        CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));

    assertEquals(CourseCode.valueOf("1234"), course.code());
    assertEquals(CourseTitle.valueOf("dummy"), course.title());
    assertEquals(CourseDescription.valueOf("dummy"), course.description());
    assertEquals(EnrolmentLimits.valueOf(10, 20), course.enrolmentLimits());
  }

  @Test
  public void ensureCheckDuplicatesWorks() {
    when(courseRepository.containsOfIdentity(CourseCode.valueOf("1234"))).thenReturn(true);

    assertThrows(IllegalStateException.class, () -> controller.createCourse(CourseCode.valueOf("1234"),
        CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20)));
  }

}
