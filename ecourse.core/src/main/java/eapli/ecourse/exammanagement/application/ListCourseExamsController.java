package eapli.ecourse.exammanagement.application;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ListCourseExamsController {

  private final AuthorizationService authz;

  private final CourseRepository courseRepository;

  private final ListCourseService service;

  private final EvaluationExamListService evaluationExamService;

  private final FormativeExamListService formativeExamService;

  public ListCourseExamsController(AuthorizationService authz, CourseRepository courseRepository,
      EvaluationExamRepository examRepository, FormativeExamRepository formativeExamRepository) {
    this.authz = authz;
    this.courseRepository = courseRepository;
    this.service = new ListCourseService(courseRepository);
    this.evaluationExamService = new EvaluationExamListService(examRepository);
    this.formativeExamService = new FormativeExamListService(formativeExamRepository);
  }

  public Iterable<CourseDTO> listOpenInProgressCourses() {
    Iterable<CourseDTO> openCourses = this.service.listOpenCourses();

    Iterable<CourseDTO> inProgressCourses = this.service.listInProgressCourses();
    Stream<CourseDTO> combinedStream = Stream.concat(StreamSupport.stream(openCourses.spliterator(), false),
        StreamSupport.stream(inProgressCourses.spliterator(), false));

    return combinedStream::iterator;
  }

  public Iterable<EvaluationExamDTO> listCourseEvaluationExams(CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.TEACHER, ClientRoles.MANAGER);

    Optional<Course> course = courseRepository.ofIdentity(courseDTO.getCode());

    if (course.isEmpty())
      throw new IllegalArgumentException("There is no Course with the given code");

    return evaluationExamService.listAllCourseExams(course.get());
  }

  public Iterable<FormativeExamDTO> listCourseFormativeExams(CourseDTO courseDTO) {
    Course course = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow();
    return formativeExamService.findAllCourseExams(course);
  }
}
