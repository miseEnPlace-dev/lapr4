package eapli.ecourse.exammanagement.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.application.ListEnrolmentService;
import eapli.ecourse.enrolmentmanagement.domain.EnrolmentState;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ListFutureExamsController {

  private final AuthorizationService authz;

  private final ListEnrolmentService enrolmentListService;

  private final StudentRepository studentRepository;

  private final CourseRepository courseRepository;

  private final EvaluationExamListService examListService;

  public ListFutureExamsController(AuthorizationService authz, EvaluationExamRepository examRepository,
                                   EnrolmentRepository enrolmentRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
    this.authz = authz;
    this.examListService = new EvaluationExamListService(examRepository);
    this.courseRepository = courseRepository;
    this.enrolmentListService = new ListEnrolmentService(enrolmentRepository);
    this.studentRepository = studentRepository;
  }

  public Iterable<CourseDTO> listStudentCourses() {
    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.STUDENT).orElseThrow();

    Student student = studentRepository.findByUsername(authenticatedUser.identity()).orElseThrow();

    List<CourseDTO> courses = new ArrayList<>();
    for (EnrolmentDTO enrolment : enrolmentListService.findByStudentMecanographicNumber(student.identity())) {
      if (enrolment.getState().equals(EnrolmentState.State.ACCEPTED.toString())) {
        Optional<Course> course = courseRepository.ofIdentity(enrolment.getCourseCode());
        course.ifPresent(value -> courses.add(value.toDto()));
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

  public Iterable<EvaluationExamDTO> futureExams(CourseDTO courseDTO) {
    return examListService.listAllFutureCourseExams(courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow());
  }

}
