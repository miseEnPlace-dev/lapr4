package eapli.ecourse.exammanagement.application;

import java.io.IOException;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamBuilder;
import eapli.ecourse.exammanagement.domain.parsers.ANTLR4TakeExamParser;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@UseCaseController
public class TakeEvaluationExamController {
  private final ListCourseService listCourseService;
  private final EvaluationExamListService examListService;
  private final AuthorizationService authz;
  private final StudentRepository studentRepository;
  private final CourseRepository courseRepository;
  private final EvaluationExamRepository examRepository;
  private final ANTLR4TakeExamParser parser;

  private Student student;

  private EvaluationExamBuilder builder;

  public TakeEvaluationExamController(final AuthorizationService authz, final StudentRepository studentRepository,
      final EvaluationExamRepository examRepository, final CourseRepository courseRepository) {
    this.authz = authz;
    this.studentRepository = studentRepository;
    this.examRepository = examRepository;
    this.courseRepository = courseRepository;
    this.listCourseService = new ListCourseService(courseRepository);
    this.examListService = new EvaluationExamListService(examRepository);
    this.parser = new ANTLR4TakeExamParser();
  }

  public void setCurrentAuthenticatedStudent() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.STUDENT);
    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.STUDENT).orElseThrow();

    student = studentRepository.findByUsername(authenticatedUser.username()).orElseThrow();
  }

  public Iterable<CourseDTO> listInProgressCoursesOfAuthenticatedStudent() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.STUDENT);
    setCurrentAuthenticatedStudent();
    return listCourseService.listInProgressCoursesThatStudentIsEnrolled(student);
  }

  public Iterable<EvaluationExamDTO> listOpenExamsForCourse(CourseDTO course) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.STUDENT);
    setCurrentAuthenticatedStudent();
    return examListService.listAllFutureCourseExams(courseRepository.ofIdentity(course.getCode()).orElseThrow());
  }

  public void parseExam(final String str, ExamPrinter printer) throws ParseException {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.STUDENT);
    setCurrentAuthenticatedStudent();
    parser.parseFromString(str, printer);
  }
}
