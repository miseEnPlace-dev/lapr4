package eapli.ecourse.coursemanagement.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;

public class AssignTeachersToCourseControllerTest extends CourseControllerBaseTest {
  private CourseRepository courseRepository;
  private TeacherRepository teacherRepository;

  private AssignTeacherToCourseController controller;

  @Before
  public void setup() {
    courseRepository = mock(CourseRepository.class);
    teacherRepository = mock(TeacherRepository.class);

    controller = new AssignTeacherToCourseController(teacherRepository, courseRepository);
  }

  @Test
  public void testListNotClosedCourses() {
    controller.allNotFinishedCourses();
    verify(courseRepository, times(1)).findAllNotFinished();
  }

  @Test
  public void testAllTeachersExceptFromCourse() {
    CourseDTO courseDTO = getDummyCourseDTO();
    when(courseRepository.ofIdentity(courseDTO.getCode())).thenReturn(Optional.of(getDummyFinishedCourse()));
    controller.allTeachersExceptFromCourse(courseDTO);
    verify(courseRepository, times(1)).ofIdentity(courseDTO.getCode());
    verify(teacherRepository, times(1)).findAll();
  }

  @Test
  public void testAssignTeachersToCourse() {
    CourseDTO courseDTO = getDummyCourseDTO();
    TeacherDTO teacherDTO = getNewTeacherDTO();

    Course c = getDummyCourse();
    when(courseRepository.ofIdentity(courseDTO.getCode())).thenReturn(Optional.of(c));
    when(teacherRepository.ofIdentity(teacherDTO.getNumber())).thenReturn(Optional.of(getNewDummyTeacher()));
    List<TeacherDTO> teachers = new ArrayList<>();
    teachers.add(teacherDTO);
    controller.assignTeachersToCourse(teachers, courseDTO);

    assertEquals(new ArrayList<Teacher>(c.teachers()).get(0).identity(), teacherDTO.getNumber());
  }
}
