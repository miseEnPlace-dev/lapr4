package eapli.ecourse.coursemanagement.application;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ListCoursesControllerTest {
  @Mock
  private AuthorizationService authz;
  @Mock
  private CourseRepository courseRepository;
  @Mock
  private TeacherRepository teacherRepository;
  @Mock
  private StudentRepository studentRepository;
  @Mock
  private EnrolmentRepository enrolmentRepository;

  private ListCoursesController listCoursesController;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    listCoursesController = new ListCoursesController(authz, courseRepository, teacherRepository, studentRepository,
        enrolmentRepository);
  }

  @Test
  public void testGetCoursesForLoggedUserAsUnauthorizedUser() {
    Iterable<CourseDTO> result = listCoursesController.getCoursesForLoggedUser();
    assertNull(result);
  }
}
