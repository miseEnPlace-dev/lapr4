package eapli.ecourse.coursemanagement.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ToggleCourseEnrolmentStateControllerTest extends CourseControllerBaseTest {

  private ToggleCourseEnrolmentStateController controller;

  private CourseRepository courseRepository;
  private AuthorizationService authz;

  @Before
  public void setUp() {
    courseRepository = mock(CourseRepository.class);
    authz = mock(AuthorizationService.class);
    controller = new ToggleCourseEnrolmentStateController(courseRepository, authz);
  }

  @Test
  public void testListNotClosedCourses() {
    controller.listNotClosedCourses();
    verify(courseRepository, times(1)).findAllNotClosed();
  }

  @Test
  public void testToggleEnrolmentState() {
    Course course = getDummyCourse();
    CourseDTO courseDTO = getDummyCourseDTO();

    when(courseRepository.ofIdentity(courseDTO.getCode())).thenReturn(Optional.of(course));
    when(courseRepository.save(course)).thenReturn(course);

    CourseDTO result = controller.toggleEnrolmentState(courseDTO);

    assertEquals(courseDTO.getCode(), result.getCode());
    assertEquals(courseDTO.getTitle(), result.getTitle());
    assertEquals(courseDTO.getDescription(), result.getDescription());
    assertNotEquals(courseDTO.getEnrolmentState(), result.getEnrolmentState());
    verify(courseRepository, times(1)).ofIdentity(courseDTO.getCode());
    verify(courseRepository, times(1)).save(course);
  }

  @Test
  public void ensureItIsNotPossibleToToggleStateOfFinishedCourse() {
    Course course = getDummyFinishedCourse();
    CourseDTO courseDTO = getDummyCourseDTO();

    when(courseRepository.ofIdentity(courseDTO.getCode())).thenReturn(Optional.of(course));
    when(courseRepository.save(course)).thenReturn(course);

    assertThrows(IllegalStateException.class, () -> controller.toggleEnrolmentState(courseDTO));
  }
}
