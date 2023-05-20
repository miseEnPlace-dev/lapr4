package eapli.ecourse.coursemanagement.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class CreateCourseControllerTest extends CourseControllerBaseTest {

  private CreateCourseController controller;
  private CourseRepository courseRepository;
  private AuthorizationService authzRegistry;
  private TeacherRepository teacherRepository;

  @Before
  public void setup() {
    authzRegistry = mock(AuthorizationService.class);
    courseRepository = mock(CourseRepository.class);
    teacherRepository = mock(TeacherRepository.class);
    controller = new CreateCourseController(courseRepository, authzRegistry,
        teacherRepository);
    when(teacherRepository.findByTaxPayerNumber(TaxPayerNumber.valueOf("1234")))
        .thenReturn(Optional.of(getNewDummyTeacher()));
  }

  @Test
  public void ensureItsNotPossibleToCreateCourseWithNullFields() {
    assertThrows(IllegalArgumentException.class, () -> controller.createCourse(null, null, null, 0, 0, null));
  }

  @Test
  public void ensureItsNotPossibleToCreateCourseWithEmptyFields() {

    assertThrows(IllegalArgumentException.class, () -> controller.createCourse("", "", "", 0, 0, null));
  }

  @Test
  public void ensureItsNotPossibleToCreateCourseWithNegativeLimits() {
    assertThrows(IllegalArgumentException.class, () -> controller.createCourse("1234", "dummy", "dummy", -1, -1, null));
  }
}
