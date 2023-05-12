package eapli.ecourse.coursemanagement.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState.EnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState.State;

public class CreateCourseControllerTest {

  private final CreateCourseController controller = new CreateCourseController();

  @Test
  public void ensureItsNotPossibleToCreateCourseWithNullFields() {

    assertThrows(IllegalArgumentException.class, () -> controller.createCourse(null, null, null, null, null, null));
  }

  // @Test
  // public void ensureItsPossibleToCreateCourse() {

  // Course course = controller.createCourse(CourseCode.valueOf("1234"),
  // CourseTitle.valueOf("dummy"),
  // CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new
  // CourseState(),
  // new CourseEnrolmentState());

  // assertEquals(CourseCode.valueOf("1234"), course.code());
  // assertEquals(CourseTitle.valueOf("dummy"), course.title());
  // assertEquals(CourseDescription.valueOf("dummy"), course.description());
  // assertEquals(EnrolmentLimits.valueOf(10, 20), course.enrolmentLimits());
  // assertEquals(new CourseState(), course.state());
  // assertEquals(new CourseEnrolmentState(), course.enrolmentState());
  // }

  // @Test
  // public void ensureItsPossibleToCreateCourseWithoutStates() {

  // Course course = controller.createCourse(CourseCode.valueOf("1234"),
  // CourseTitle.valueOf("dummy"),
  // CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));

  // assertEquals(CourseCode.valueOf("1234"), course.code());
  // assertEquals(CourseTitle.valueOf("dummy"), course.title());
  // assertEquals(CourseDescription.valueOf("dummy"), course.description());
  // assertEquals(EnrolmentLimits.valueOf(10, 20), course.enrolmentLimits());
  // }

  // @Test
  // public void ensureItsPossibleToCreateCourseWithOpenState() {

  // Course course = controller.createCourse(CourseCode.valueOf("1234"),
  // CourseTitle.valueOf("dummy"),
  // CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new
  // CourseState(CourseState.State.OPEN),
  // new CourseEnrolmentState(EnrolmentState.OPEN));

  // assertEquals(new CourseState(CourseState.State.OPEN), course.state());
  // assertEquals(new CourseEnrolmentState(EnrolmentState.OPEN),
  // course.enrolmentState());
  // }

  // @Test
  // public void ensureItsPossibleToCreateCourseWithClosedState() {

  // Course course = controller.createCourse(CourseCode.valueOf("1234"),
  // CourseTitle.valueOf("dummy"),
  // CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20),
  // new CourseState(CourseState.State.CLOSED), new CourseEnrolmentState());

  // assertEquals(new CourseState(CourseState.State.CLOSED), course.state());
  // assertEquals(new CourseEnrolmentState(EnrolmentState.CLOSED),
  // course.enrolmentState());

  // }

}
