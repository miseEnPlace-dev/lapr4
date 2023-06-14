package eapli.ecourse.exammanagement.application;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamBuilder;
import eapli.ecourse.exammanagement.domain.parsers.ANTLR4ExamParser;
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@UseCaseController
public class TakeFormativeExamController {
  private final ListCourseService listCourseService;
  private final AuthorizationService authz;
  private final StudentRepository studentRepository;
  private final CourseRepository courseRepository;
  private final FormativeExamRepository formativeExamRepository;
  private final ANTLR4ExamParser parser;
  private final FormativeExamListService service;

  private Student student;

  private EvaluationExamBuilder builder;

  public TakeFormativeExamController(final AuthorizationService authz, final StudentRepository studentRepository,
      final CourseRepository courseRepository, final FormativeExamRepository formativeExamRepository) {
    this.authz = authz;
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
    this.listCourseService = new ListCourseService(courseRepository);
    this.parser = new ANTLR4ExamParser();
    this.formativeExamRepository = formativeExamRepository;
    this.service = new FormativeExamListService(formativeExamRepository);
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

  public Iterable<FormativeExamDTO> listFormativeExams(CourseDTO courseDTO) {

    return service.findAllCourseExams(courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow());
  }
}
