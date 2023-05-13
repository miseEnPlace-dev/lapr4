package eapli.ecourse.coursemanagement.domain;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import eapli.ecourse.coursemanagement.dto.CourseDTO;

public class CourseTest {
  private Course getDummyCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(),
        new CourseEnrolmentState());
  }

  private Course getDummyOpenCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(CourseState.State.OPEN),
        new CourseEnrolmentState());
  }

  private Course getDummyClosedCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(CourseState.State.CLOSED),
        new CourseEnrolmentState());
  }

  private Course getDummyInProgressCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.IN_PROGRESS), new CourseEnrolmentState());
  }

  private Course getDummyFinishedCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.FINISHED), new CourseEnrolmentState());
  }

  @Test
  public void ensureCourseHasCode() {
    assertThrows(IllegalArgumentException.class, () -> new Course(null, CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(),
        new CourseEnrolmentState()));
  }

  @Test
  public void ensureCourseHasTitle() {
    assertThrows(IllegalArgumentException.class, () -> new Course(CourseCode.valueOf("1234"), null,
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(),
        new CourseEnrolmentState()));
  }

  @Test
  public void ensureCourseHasDescription() {
    assertThrows(IllegalArgumentException.class,
        () -> new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
            null, EnrolmentLimits.valueOf(10, 20), new CourseState(), new CourseEnrolmentState()));
  }

  @Test
  public void ensureCourseHasEnrolmentLimits() {
    assertThrows(IllegalArgumentException.class,
        () -> new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
            CourseDescription.valueOf("dummy"), null, new CourseState(), new CourseEnrolmentState()));
  }

  @Test
  public void ensureCourseHasCourseState() {
    assertThrows(IllegalArgumentException.class,
        () -> new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
            CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), null, new CourseEnrolmentState()));
  }

  @Test
  public void ensureCourseHasEnrolmentState() {
    assertThrows(IllegalArgumentException.class,
        () -> new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
            CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(), null));
  }

  @Test
  public void ensureCourseHasDefaultCourseState() {
    final Course course = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));
    assertTrue(course.state().isClosed());
  }

  @Test
  public void ensureCourseHasDefaultEnrolmentState() {
    final Course course = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));
    assertTrue(course.enrolmentState().isClosed());
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
  public void ensureCourseIsNotSameAsWithDifferentClass() {
    final Course course = new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20));

    assertFalse(course.sameAs(new Object()));
  }

  @Test
  public void ensureCourseIsSameAsWithSameInstance() {
    final Course course = getDummyCourse();

    assertTrue(course.sameAs(course));
  }

  @Test
  public void ensureCourseIsEqualToSameInstance() {
    final Course course = getDummyCourse();

    assertEquals(course, course);
  }

  @Test
  public void ensureCourseIsInCorrectStateAfterToggle() {
    final Course course = getDummyOpenCourse();

    assertTrue(course.enrolmentState().isClosed());
    course.toggleEnrolmentState();
    assertTrue(course.enrolmentState().isOpen());
  }

  @Test
  public void ensureDoubleToggleDoesNotChangeState() {
    final Course course = getDummyOpenCourse();

    assertTrue(course.enrolmentState().isClosed());
    course.toggleEnrolmentState();
    course.toggleEnrolmentState();
    assertTrue(course.enrolmentState().isClosed());
  }

  @Test
  public void ensureCannotOpenEnrolmentsInClosedCourse() {
    final Course course = getDummyClosedCourse();

    assertTrue(course.state().isClosed());
    assertTrue(course.enrolmentState().isClosed());
    assertThrows(IllegalStateException.class, () -> course.toggleEnrolmentState());
  }

  @Test
  public void ensureCannotOpenEnrolmentsInFinishedCourse() {
    final Course course = getDummyFinishedCourse();

    assertTrue(course.state().isFinished());
    assertThrows(IllegalStateException.class, () -> course.toggleEnrolmentState());
  }

  @Test
  public void ensureIsPossibleToToggleEnrolmentsInProgressCourse() {
    final Course course = getDummyInProgressCourse();

    assertTrue(course.state().isInProgress());
    assertTrue(course.enrolmentState().isClosed());
    course.toggleEnrolmentState();
    assertTrue(course.enrolmentState().isOpen());
  }

  @Test
  public void ensureIsPossibleToToggleEnrolmentsInOpenCourse() {
    final Course course = getDummyOpenCourse();

    assertTrue(course.state().isOpen());
    assertTrue(course.enrolmentState().isClosed());
    course.toggleEnrolmentState();
    assertTrue(course.enrolmentState().isOpen());
  }

  @Test
  public void ensureIsPossibleToCreateDto() {
    final Course course = getDummyCourse();

    final CourseDTO dto = course.toDto();
    assertEquals(course.code(), dto.getCode());
    assertEquals(course.title(), dto.getTitle());
    assertEquals(course.description(), dto.getDescription());
    assertEquals(course.enrolmentLimits(), dto.getEnrolmentLimits());
    assertEquals(course.state(), dto.getCourseState());
    assertEquals(course.enrolmentState(), dto.getEnrolmentState());
  }
}
