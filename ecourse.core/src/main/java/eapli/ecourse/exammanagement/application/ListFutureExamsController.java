package eapli.ecourse.exammanagement.application;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.exammanagement.domain.dto.ExamDTO;
import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListFutureExamsController {

  private final AuthorizationService authz;

  private final EnrolmentRepository enrolmentRepository;

  private final StudentRepository studentRepository;

  private final ExamListService service;

  private final CourseRepository courseRepository;

  public ListFutureExamsController(AuthorizationService authz, ExamRepository examRepository, EnrolmentRepository enrolmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
    this.authz = authz;
    this.service = new ExamListService(examRepository);
    this.courseRepository = courseRepository;
    this.enrolmentRepository = enrolmentRepository;
    this.studentRepository = studentRepository;
  }


  public Iterable<CourseDTO> listStudentCourses() {
    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.STUDENT).orElseThrow();

    Student student = studentRepository.findByUsername(authenticatedUser.identity()).orElseThrow();

    List<CourseDTO> courses = new ArrayList<>();
    for (Enrolment enrolment : enrolmentRepository.findByStudentMecanographicNumber(student.identity())) {
      if (enrolment.isAccepted())
        courses.add(enrolment.course().toDto());
    }

    return () -> new Iterator<CourseDTO>() {
      private int index = 0;
      private List<CourseDTO> coursesDTO = courses;

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
    return service.listAllFutureCourseExams(courseRepository.findByCode(courseDTO.getCode()).orElseThrow());
  }

}
