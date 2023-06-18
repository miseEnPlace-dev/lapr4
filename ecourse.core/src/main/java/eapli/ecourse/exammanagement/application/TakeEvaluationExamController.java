package eapli.ecourse.exammanagement.application;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.repositories.AnswerRepository;
import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.exammanagement.domain.parsers.ANTLR4TakeExamParser;
import eapli.ecourse.exammanagement.domain.parsers.GrammarParser;
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
  private final AnswerRepository answerRepository;
  private final EvaluationExamRepository examRepository;
  private final GrammarParser<ExamScore> parser;

  private Student student;
  private ExamScore studentsScore;

  public TakeEvaluationExamController(final AuthorizationService authz,
      final StudentRepository studentRepository, final EvaluationExamRepository examRepository,
      final CourseRepository courseRepository, final AnswerRepository answerRepository) {
    this.authz = authz;
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
    this.answerRepository = answerRepository;
    this.examRepository = examRepository;
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
    return examListService
        .listAllOpenCourseExams(courseRepository.ofIdentity(course.getCode()).orElseThrow());
  }

  public boolean hasTakenExam(EvaluationExamDTO exam) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.STUDENT);
    if (answerRepository.findAllWithStudentAndExam(student.identity(), exam.getIdentifier()).iterator().hasNext())
      return true;
    return false;
  }

  public void parseExam(final String str, ExamPrinter printer) throws ParseException {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.STUDENT);
    setCurrentAuthenticatedStudent();
    studentsScore = parser.parseFromString(str, printer);
  }

  public void saveAnswer(EvaluationExamDTO exam) {
    EvaluationExam evaluationExam = examRepository.ofIdentity(exam.getIdentifier()).orElseThrow();

    Answer answer = new Answer(student, evaluationExam, studentsScore);

    answerRepository.save(answer);
  }
}
