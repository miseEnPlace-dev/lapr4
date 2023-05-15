package eapli.ecourse.coursemanagement.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.domain.TeacherBuilder;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class CreateCourseControllerTest {

  private CreateCourseController controller;
  private CourseRepository courseRepository;
  private AuthorizationService authzRegistry;
  private TeacherRepository teacherRepository;

  @BeforeEach
  public void setup() {
    authzRegistry = mock(AuthorizationService.class);
    courseRepository = mock(CourseRepository.class);
    teacherRepository = mock(TeacherRepository.class);
    controller = new CreateCourseController(courseRepository, authzRegistry, teacherRepository);
  }

  private TeacherDTO getDummyTeacherDTO() {
    return new TeacherDTO(TaxPayerNumber.valueOf("1234"), Acronym.valueOf("abc"),
        BirthDate.valueOf(Calendar.getInstance()), Username.valueOf("aaa"));
  }

  private Teacher getDummyTeacher() {
    return new TeacherBuilder().withTaxPayerNumber("1234").build();
  }

  // @Test
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

  // @Test
  public void ensureItsPossibleToCreateCourses() {
    when(teacherRepository.findByTaxPayerNumber(TaxPayerNumber.valueOf("1234"))).thenReturn(Optional.of(getDummyTeacher()));
    Course course = controller.createCourse("1234", "dummy", "dummy", 10, 20, getDummyTeacherDTO());

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

      assertThrows(IllegalArgumentException.class, () -> controller.createCourse("1234", "dummy", "dummy", 10, 20, getDummyTeacherDTO()));
  }

}
