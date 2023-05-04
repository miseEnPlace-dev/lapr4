package eapli.ecourse.coursemanagement.domain;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class CourseTest {
  @Test
  public void ensureCourseHasCode() {
    assertThrows(IllegalArgumentException.class, () -> new Course(null, CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), CourseState.CLOSED,
        CourseEnrolmentState.CLOSED));
  }

  @Test
  public void ensureCourseHasTitle() {
    assertThrows(IllegalArgumentException.class, () -> new Course(CourseCode.valueOf("1234"), null,
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), CourseState.CLOSED,
        CourseEnrolmentState.CLOSED));
  }

  @Test
  public void ensureCourseHasDescription() {
    assertThrows(IllegalArgumentException.class,
        () -> new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
            null, EnrolmentLimits.valueOf(10, 20), CourseState.CLOSED, CourseEnrolmentState.CLOSED));
  }

  @Test
  public void ensureCourseHasEnrolmentLimits() {
    assertThrows(IllegalArgumentException.class,
        () -> new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
            CourseDescription.valueOf("dummy"), null, CourseState.CLOSED, CourseEnrolmentState.CLOSED));
  }

  @Test
  public void ensureCourseHasCourseState() {
    assertThrows(IllegalArgumentException.class,
        () -> new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
            CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), null, CourseEnrolmentState.CLOSED));
  }

  @Test
  public void ensureCourseHasEnrolmentState() {
    assertThrows(IllegalArgumentException.class,
        () -> new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
            CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), CourseState.CLOSED, null));
  }

  @Test
  public void ensureCourseHasDefaultCourseState() {
    final Course course = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));
    assertTrue(course.state().equals(CourseState.CLOSED));
  }

  @Test
  public void ensureCourseHasDefaultEnrolmentState() {
    final Course course = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));
    assertTrue(course.enrolmentState().equals(CourseEnrolmentState.CLOSED));
  }

  @Test
  public void ensureCourseIdIsUnique() {
    final Course course1 = new Course(CourseCode.newID(), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));
    final Course course2 = new Course(CourseCode.newID(), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));

    assertNotEquals(course1.identity(), course2.identity());
  }

  @Test
  public void ensureCourseIsEqualWithSameCode() {
    final Course course1 = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy1"),
        CourseDescription.valueOf("dummy1"), EnrolmentLimits.valueOf(10, 20));
    final Course course2 = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy2"),
        CourseDescription.valueOf("dummy2"), EnrolmentLimits.valueOf(10, 20));

    assertEquals(course1, course2);
  }

  @Test
  public void ensureCourseIsEqualButNotSame() {
    final Course course1 = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy1"),
        CourseDescription.valueOf("dummy1"), EnrolmentLimits.valueOf(10, 20));
    final Course course2 = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy2"),
        CourseDescription.valueOf("dummy2"), EnrolmentLimits.valueOf(10, 20));

    assertEquals(course1, course2);
    assertFalse(course1.sameAs(course2));
  }

  @Test
  public void ensureCourseIsTheSameWithEqualAttributes() {
    final Course course1 = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));
    final Course course2 = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));

    assertTrue(course1.sameAs(course2));
  }

  @Test
  public void ensureCourseIsEqualToSameInstance() {
    final Course course = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));

    assertEquals(course, course);
  }
}
