package eapli.ecourse.coursemanagement.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.repositories.CourseRepository;

public class ListCourseServiceTest extends CourseControllerBaseTest {
  private ListCourseService service;
  private CourseRepository courseRepository;

  @Before
  public void setup() {
    courseRepository = mock(CourseRepository.class);
    service = new ListCourseService(courseRepository);
  }

  @Test
  public void ensureListAllCourses() {
    service.listClosedCourses();
    verify(courseRepository, times(1)).findAllClosed();
  }

  @Test
  public void ensureListAllOpenCourses() {
    service.listOpenCourses();
    verify(courseRepository, times(1)).findAllOpen();
  }

  @Test
  public void ensureListAllNotCLosedCourses() {
    service.listNotClosedCourses();
    verify(courseRepository, times(1)).findAllNotClosed();
  }

  @Test
  public void ensureListAllInProgress() {
    service.listInProgressCourses();
    verify(courseRepository, times(1)).findAllInProgress();
  }

  @Test
  public void ensureListAllOpenForEnrolments() {
    service.listOpenForEnrolment();
    verify(courseRepository, times(1)).findAllOpenForEnrolment();
  }
}
