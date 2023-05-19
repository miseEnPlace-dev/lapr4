package eapli.ecourse.coursemanagement.application;

import java.util.Set;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.teachermanagement.application.TeacherService;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;

public class AssignTeacherToCourseController {
  private CourseRepository courseRepository;
  private TeacherService teacherService;
  private ListCourseService listCourseService;

  public AssignTeacherToCourseController(TeacherRepository teacherRepository, CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
    this.teacherService = new TeacherService(teacherRepository);
    this.listCourseService = new ListCourseService(courseRepository);
  }

  public Iterable<TeacherDTO> allTeachersExceptFromCourse(CourseDTO courseDTO) {
    Set<Teacher> teachersFromCourse = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow().teachers();

    return teacherService.allTeachersExceptFromCourse(teachersFromCourse);
  }

  public Iterable<CourseDTO> allNotFinishedCourses() {
    return listCourseService.listNotFinishedCourses();
  }

  public Course assignTeachersToCourse(Iterable<TeacherDTO> teachers, CourseDTO courseDTO) {
    Course course = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow();

    course.addTeachers(teacherService.toDomain(teachers));
    return courseRepository.save(course);
  }
}
