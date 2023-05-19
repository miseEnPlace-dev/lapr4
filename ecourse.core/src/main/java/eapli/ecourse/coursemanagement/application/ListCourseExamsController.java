package eapli.ecourse.coursemanagement.application;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.application.ExamListService;
import eapli.ecourse.exammanagement.dto.ExamDTO;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ListCourseExamsController {

  private final AuthorizationService authz;

  private final CourseRepository courseRepository;

  private final ListCourseService service;

  private final ExamListService examService;

  public ListCourseExamsController(AuthorizationService authz, CourseRepository courseRepository,
      ExamRepository examRepository) {
    this.authz = authz;
    this.courseRepository = courseRepository;
    this.service = new ListCourseService(courseRepository);
    this.examService = new ExamListService(examRepository);
  }

  public Iterable<CourseDTO> listOpenInProgressCourses() {
    Iterable<CourseDTO> openCourses = this.service.listOpenCourses();

    Iterable<CourseDTO> inProgressCourses = this.service.listInProgressCourses();
    Stream<CourseDTO> combinedStream = Stream.concat(StreamSupport.stream(openCourses.spliterator(), false),
        StreamSupport.stream(inProgressCourses.spliterator(), false));

    return combinedStream::iterator;
  }

  public Iterable<ExamDTO> listCourseExams(CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.TEACHER, ClientRoles.MANAGER);

    Optional<Course> course = courseRepository.findByCode(courseDTO.getCode());

    if (course.isEmpty())
      throw new IllegalArgumentException("There is no Course with the given code");

    return examService.listAllCourseExams(course.get());
  }
}
