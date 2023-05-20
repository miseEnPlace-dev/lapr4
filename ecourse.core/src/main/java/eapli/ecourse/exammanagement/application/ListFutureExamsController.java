package eapli.ecourse.exammanagement.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.application.ListEnrolmentService;
import eapli.ecourse.enrolmentmanagement.domain.EnrolmentState;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.exammanagement.dto.ExamDTO;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.studentmanagement.application.StudentService;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ListFutureExamsController {

  private final AuthorizationService authz;

  private final ListEnrolmentService enrolmentListService;

  private final StudentService studentListService;

  private final CourseRepository courseRepository;

  private final ListCourseService courseService;

  private final ExamListService examListService;

  public ListFutureExamsController(AuthorizationService authz, EvaluationExamRepository examRepository,
      EnrolmentRepository enrolmentRepository, CourseRepository courseRepository) {
    this.authz = authz;
    this.examListService = new ExamListService(examRepository);
    this.courseRepository = courseRepository;
    this.courseService = new ListCourseService(courseRepository);
    this.enrolmentListService = new ListEnrolmentService(enrolmentRepository);
    this.studentListService = new StudentService();
  }

  public Iterable<CourseDTO> listStudentCourses() {
    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.STUDENT).orElseThrow();

    Student student = studentListService.findStudentUserByUsername(authenticatedUser.identity()).orElseThrow();

    List<CourseDTO> courses = new ArrayList<>();
    for (EnrolmentDTO enrolment : enrolmentListService.findByStudentMecanographicNumber(student.identity())) {
      if (enrolment.getState().equals(EnrolmentState.State.ACCEPTED.toString())) {
        Optional<CourseDTO> course = courseService.findByCode(enrolment.getCourseCode());
        course.ifPresent(courses::add);
      }
    }

    return () -> new Iterator<CourseDTO>() {
      private int index = 0;
      private final List<CourseDTO> coursesDTO = courses;

      @Override
      public boolean hasNext() {
        return index < coursesDTO.size();
      }

      @Override
      public CourseDTO next() {
        return coursesDTO.get(index++);
      }
    };
  }

  public Iterable<ExamDTO> futureExams(CourseDTO courseDTO) {
    return examListService.listAllFutureCourseExams(courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow());
  }

}
