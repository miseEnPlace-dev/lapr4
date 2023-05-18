package eapli.ecourse.coursemanagement.application;

import java.util.HashSet;
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
  private TeacherRepository teacherRepository;
  private TeacherService teacherService;
  private ListCourseService listCourseService;

  public AssignTeacherToCourseController(TeacherRepository teacherRepository, CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
    this.teacherRepository = teacherRepository;
    this.teacherService = new TeacherService(teacherRepository);
    this.listCourseService = new ListCourseService(courseRepository);
  }

  public Iterable<TeacherDTO> allTeachersExceptFromCourse(CourseDTO courseDTO) {
    Set<Teacher> teachersFromCourse = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow().teachers();
    Iterable<TeacherDTO> allTeachers = teacherService.allTeachers();
    Set<TeacherDTO> teachersExceptFromCourse = new HashSet<>();

    for (TeacherDTO teacher : allTeachers)
      for (Teacher t : teachersFromCourse)
        if (!teacher.getNumber().equals(t.taxPayerNumber()))
          teachersExceptFromCourse.add(teacher);

    return teachersExceptFromCourse;
  }

  public Iterable<CourseDTO> allNotClosedCourses() {
    return listCourseService.listNotFinishedCourses();
  }

  public Course assignTeachersToCourse(Iterable<TeacherDTO> teacherDTO, CourseDTO courseDTO) {
    Course course = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow();
    Set<Teacher> teachers = new HashSet<>();
    for (TeacherDTO teacher : teacherDTO) {
      Teacher t = teacherRepository.ofIdentity(teacher.getNumber()).orElseThrow();
      teachers.add(t);
    }
    course.addTeachers(teachers);
    return courseRepository.save(course);
  }
}
