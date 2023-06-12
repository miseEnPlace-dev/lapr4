package eapli.ecourse.exammanagement.application;

import java.io.IOException;
import java.util.Collection;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequestBuilder;
import eapli.ecourse.exammanagement.domain.parsers.ANTLR4FormativeExamParser;
import eapli.ecourse.exammanagement.repositories.FormativeExamRequestRepository;
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
  private final FormativeExamRequestRepository formativeExamRequestRepository;

  private final ListCourseService listCourseService;
  private final FormativeExamService examService;

  private final ANTLR4FormativeExamParser parser;

  private Teacher teacher;
  private FormativeExamRequestBuilder builder;

  public CreateFormativeExamController(final AuthorizationService authz, final TeacherRepository teacherRepository,
      final CourseRepository courseRepository, final QuestionRepository questionRepository,
      final FormativeExamRequestRepository formativeExamRequestRepository) {
    this.authz = authz;
    this.teacherRepository = teacherRepository;
    this.courseRepository = courseRepository;

    this.listCourseService = new ListCourseService(courseRepository);
    this.examService = new FormativeExamService(questionRepository);

    this.parser = new ANTLR4FormativeExamParser();
    this.formativeExamRequestRepository = formativeExamRequestRepository;
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
    builder = parser.parseFromFile(filePath);
  }

  public void createExam(CourseDTO courseDto) {
    setCurrentAuthenticatedTeacher();
    Course course = courseRepository.ofIdentity(courseDto.getCode()).orElseThrow();

    FormativeExamRequest request = builder.build();

    formativeExamRequestRepository.save(request);
  }
}
