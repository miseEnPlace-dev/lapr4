package eapli.ecourse.answermanagement.application;

import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.ecourse.answermanagement.repositories.ExamAnswerRepository;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.enrolmentmanagement.application.ListEnrolmentService;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ListStudentGradesController {
  private final StudentRepository studentRepository;
  private final AuthorizationService authz;

  private final ListEnrolmentService listEnrolmentService;
  private final ListExamAnswerService listExamAnswerService;

  public ListStudentGradesController(EnrolmentRepository enrolmentRepository, ExamAnswerRepository examAnswerRepository,
      StudentRepository studentRepository,
      EvaluationExamRepository evaluationExamRepository, AuthorizationService authorizationService) {
    this.studentRepository = studentRepository;
    this.authz = authorizationService;

    this.listEnrolmentService = new ListEnrolmentService(enrolmentRepository);
    this.listExamAnswerService = new ListExamAnswerService(examAnswerRepository, evaluationExamRepository);
  }

  private Student getAuthenticatedStudent() {
    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.STUDENT).orElseThrow();

    return studentRepository.findByUsername(authenticatedUser.username()).orElseThrow();
  }

  public Iterable<AnswerDTO> listStudentGrades(CourseDTO course) {
    Student student = getAuthenticatedStudent();
    return listExamAnswerService.listStudentGrades(student, course.getCode());
  }

  public Iterable<CourseDTO> listStudentCourses() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT, ClientRoles.POWER_USER);
    Student student = getAuthenticatedStudent();

    return listEnrolmentService.listStudentCourses(student.identity());
  }
}
