package eapli.ecourse.questionmanagement.application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.parsers.ANTLR4QuestionParser;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class AddQuestionsController {
  private final QuestionRepository questionRepository;
  private final CourseRepository courseRepository;
  private final ListCourseService courseListService;
  private final AuthorizationService authz;
  private final ANTLR4QuestionParser parser;

  public AddQuestionsController(QuestionRepository questionRepository, CourseRepository courseRepository,
      AuthorizationService authz) {
    this.questionRepository = questionRepository;
    this.courseRepository = courseRepository;
    this.authz = authz;

    this.courseListService = new ListCourseService(courseRepository);

    this.parser = new ANTLR4QuestionParser();
  }

  public Iterable<CourseDTO> listAvailableCourses() {
    return this.courseListService.listNotClosedCourses();
  }

  public void addQuestionsFromFile(String filename, CourseDTO courseDTO) throws IOException, ParseException {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.TEACHER);

    if (!fileExists(filename))
      throw new IllegalArgumentException("Invalid file path!");

    Course course = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow();
    List<Question> questions = parser.parseFromFile(filename);

    questions.forEach(question -> {
      question.changeCourse(course);
      addQuestion(question);
    });
  }

  public boolean fileExists(String filename) {
    if (filename == null || filename.isEmpty())
      return false;

    File f = new File(filename);
    if (!f.exists() || f.isDirectory())
      return false;

    return true;
  }

  public Question addQuestion(Question question) {
    return questionRepository.save(question);
  }
}
