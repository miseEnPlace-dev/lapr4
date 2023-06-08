package eapli.ecourse.coursemanagement.application;

import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.teachermanagement.application.TeacherService;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class CreateCourseController {
  private final TeacherService service;
  private final CourseRepository courseRepository;
  private final AuthorizationService authz;
  private final TeacherRepository teacherRepository;

  public CreateCourseController(final CourseRepository courseRepository, final AuthorizationService authz,
      final TeacherRepository teacherRepository) {
    this.courseRepository = courseRepository;
    this.authz = authz;
    this.teacherRepository = teacherRepository;
    this.service = new TeacherService(this.teacherRepository);
  }

  public Course createCourse(String code, String title, String description,
      int min, int max, TeacherDTO teacherDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Preconditions.noneNull(code, title, description, min, max, teacherDTO);

    Teacher teacher = teacherRepository.findByTaxPayerNumber(teacherDTO.getNumber()).orElseThrow();

    Course course = new CourseBuilder().withCode(code).withTitle(title).withDescription(description)
        .withEnrolmentLimits(min, max).withResponsibleTeacher(teacher).build();

    if (courseRepository.containsOfIdentity(course.code()))
      throw new IllegalStateException("There is already a course with that code.");

    return saveCourse(course);
  }

  private Course saveCourse(Course course) {
    if (course == null)
      throw new IllegalArgumentException("Course cannot be null.");

    return courseRepository.save(course);
  }

  public Iterable<TeacherDTO> listAllTeachers() {
    return service.allTeachers();
  }

  public Optional<Course> checkIfCourseCodeExists(CourseCode code) {
    return courseRepository.findByCode(code);
  }
}
