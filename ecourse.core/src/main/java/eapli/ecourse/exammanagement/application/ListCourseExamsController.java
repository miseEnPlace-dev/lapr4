package eapli.ecourse.exammanagement.application;

import java.util.Optional;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ListCourseExamsController {

  private final AuthorizationService authz;
  private final CourseRepository courseRepository;
  private final ListCourseService service;
  private final EvaluationExamListService evaluationExamService;
  private final TeacherRepository teacherRepository;

  public ListCourseExamsController(AuthorizationService authz, CourseRepository courseRepository,
      EvaluationExamRepository examRepository, TeacherRepository teacherRepository) {
    this.authz = authz;
    this.courseRepository = courseRepository;
    this.service = new ListCourseService(courseRepository);
    this.evaluationExamService = new EvaluationExamListService(examRepository);
    this.teacherRepository = teacherRepository;
  }

  public Iterable<CourseDTO> listOpenInProgressCourses() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.TEACHER);

    Teacher teacher = teacherRepository.findByUsername(authz.session().get().authenticatedUser().identity())
      .orElseThrow();
    return this.service.listInProgressCoursesThatTeacherLectures(teacher);
  }

  public Iterable<EvaluationExamDTO> listCourseEvaluationExams(CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.TEACHER);

    Optional<Course> course = courseRepository.ofIdentity(courseDTO.getCode());

    if (course.isEmpty())
      throw new IllegalArgumentException("There is no Course with the given code");

    return evaluationExamService.listAllCourseExams(course.get());
  }
}
