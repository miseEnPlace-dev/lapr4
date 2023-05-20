package eapli.ecourse.exammanagement.application;

import java.io.IOException;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequestBuilder;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSectionRequest;
import eapli.ecourse.exammanagement.domain.parsers.FormativeExamsParser;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CreateFormativeExamController {
  private final AuthorizationService authz;
  private final TeacherRepository teacherRepository;
  private final CourseRepository courseRepository;
  private final FormativeExamRepository examRepository;
  private final QuestionRepository questionRepository;

  private final ListCourseService listCourseService;
  private final FormativeExamService examService;

  private Teacher teacher;
  private FormativeExamRequestBuilder builder;

  public CreateFormativeExamController(final AuthorizationService authz, final TeacherRepository teacherRepository,
      final FormativeExamRepository examRepository, final CourseRepository courseRepository,
      final QuestionRepository questionRepository) {
    this.authz = authz;
    this.teacherRepository = teacherRepository;
    this.examRepository = examRepository;
    this.courseRepository = courseRepository;
    this.questionRepository = questionRepository;

    this.listCourseService = new ListCourseService(courseRepository);
    this.examService = new FormativeExamService(questionRepository);
  }

  public void setCurrentAuthenticatedTeacher() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.TEACHER);
    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.TEACHER).orElseThrow();

    teacher = teacherRepository.findByUsername(authenticatedUser.username()).orElseThrow();
  }

  public Iterable<CourseDTO> listInProgressCoursesOfAuthenticatedTeacher() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.TEACHER);
    setCurrentAuthenticatedTeacher();
    return listCourseService.listInProgressCoursesThatTeacherLectures(teacher);
  }

  public void parseExam(final String filePath) throws IOException, ParseException {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.TEACHER);
    setCurrentAuthenticatedTeacher();
    builder = FormativeExamsParser.parseWithVisitor(filePath);
  }

  public void createExam(CourseDTO courseDto) {
    setCurrentAuthenticatedTeacher();
    Course course = courseRepository.ofIdentity(courseDto.getCode()).orElseThrow();

    FormativeExamRequest request = builder.build();
    for (FormativeExamSectionRequest sectionRequest : request.sections()) {
      Iterable<Question> questions = examService.buildSection(sectionRequest.numberOfQuestions(),
          sectionRequest.questionsType(),
          courseDto);
    }

    // TODO
    // builder.withTeacher(teacher).withCourse(course);

    // FormativeExam exam = examRepository.save(exam);
  }
}
