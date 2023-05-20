package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.application.ListEnrolmentService;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ListCoursesController {
  private AuthorizationService authz;
  private TeacherRepository teacherRepository;
  private StudentRepository studentRepository;

  private ListCourseService listCourseService;
  private ListEnrolmentService enrolmentListService;

  public ListCoursesController(AuthorizationService authz, CourseRepository courseRepository,
      TeacherRepository teacherRepository, StudentRepository studentRepository,
      EnrolmentRepository enrolmentRepository) {
    this.authz = authz;
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;

    this.listCourseService = new ListCourseService(courseRepository);
    this.enrolmentListService = new ListEnrolmentService(enrolmentRepository);
  }

  public Iterable<CourseDTO> getForLoggedUser() {
    if (authz.isAuthenticatedUserAuthorizedTo(ClientRoles.MANAGER, ClientRoles.POWER_USER))
      return listCourseService.listAll();

    if (authz.isAuthenticatedUserAuthorizedTo(ClientRoles.TEACHER)) {
      final SystemUser user = authz.loggedinUserWithPermissions(ClientRoles.TEACHER).orElseThrow();
      final Teacher teacher = teacherRepository.findByUsername(user.username()).orElseThrow();
      return listCourseService.listInProgressCoursesThatTeacherLectures(teacher);
    }

    if (authz.isAuthenticatedUserAuthorizedTo(ClientRoles.STUDENT)) {
      final SystemUser user = authz.loggedinUserWithPermissions(ClientRoles.STUDENT).orElseThrow();
      final Student student = studentRepository.findByUsername(user.username()).orElseThrow();
      return enrolmentListService
          .listStudentsCourses(student.identity());
    }

    return null;
  }
}
