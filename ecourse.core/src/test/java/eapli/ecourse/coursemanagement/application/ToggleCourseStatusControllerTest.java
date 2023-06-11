package eapli.ecourse.coursemanagement.application;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ToggleCourseStatusControllerTest {
  @Mock
  private CourseRepository courseRepository;
  @Mock
  private AuthorizationService authz;

  // private ListCourseService listCourseService;
  private ToggleCourseStatusController toggleCourseStatusController;

  private CourseDTO getDummyCourseDTO() {
    return new CourseDTO(CourseCode.valueOf("id"), CourseTitle.valueOf("Test Course"),
        CourseDescription.valueOf("Test Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.CLOSED), new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED),
        Calendar.getInstance());
  }

  public Course getDummyCourse() {
    return new Course(CourseCode.valueOf("id"), CourseTitle.valueOf("Test Course"),
        CourseDescription.valueOf("Test Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.CLOSED), new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED),
        null);
  }

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    // listCourseService = new ListCourseService(courseRepository);
    toggleCourseStatusController = new ToggleCourseStatusController(courseRepository, authz);
  }

  @Test
  public void testListOpenCourses() {
    toggleCourseStatusController.listOpenCourses();
    verify(courseRepository, times(1)).findAllOpen();
  }

  @Test
  public void testListClosedCourses() {
    toggleCourseStatusController.listClosedCourses();
    verify(courseRepository, times(1)).findAllClosed();
  }

  @Test
  public void testListInProgressCourses() {
    toggleCourseStatusController.listInProgressCourses();
    verify(courseRepository, times(1)).findAllInProgress();
  }

  @Test
  public void testToggleToNextState() {
    final CourseDTO d = getDummyCourseDTO();
    final Course course = getDummyCourse();
    when(courseRepository.ofIdentity(d.getCode())).thenReturn(Optional.of(course));
    assertTrue(course.state().isClosed());

    toggleCourseStatusController.toggleToNextCourseStatus(d);

    verify(courseRepository, times(1)).ofIdentity(d.getCode());
    verify(courseRepository, times(1)).save(course);
    assertTrue(course.state().isOpen());
  }

  @Test
  public void testToggleToPreviousStateThrowsExceptionOnClosedCourse() {
    final CourseDTO d = getDummyCourseDTO();
    final Course course = getDummyCourse();
    when(courseRepository.ofIdentity(d.getCode())).thenReturn(Optional.of(course));
    assertTrue(course.state().isClosed());

    assertThrows(IllegalStateException.class, () -> toggleCourseStatusController.toggleToPreviousCourseStatus(d));

  }
}
