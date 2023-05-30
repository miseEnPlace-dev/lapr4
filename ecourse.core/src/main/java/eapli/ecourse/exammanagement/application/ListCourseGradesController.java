package eapli.ecourse.exammanagement.application;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.enrolmentmanagement.application.ListEnrolmentService;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.studentmanagement.application.StudentService;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ListCourseGradesController {

  private final ListEnrolmentService enrolmentService;

  private final EvaluationExamListService examService;

  private final AuthorizationService authz;

  private final StudentService studentService;

  private final ListCourseService courseService;

  private final TeacherRepository teacherRepository;


  public ListCourseGradesController(ListEnrolmentService enrolmentService, EvaluationExamListService examService, AuthorizationService authz,
                                    StudentService studentService, ListCourseService courseService, TeacherRepository teacherRepository) {
    this.enrolmentService = enrolmentService;
    this.examService = examService;
    this.authz = authz;
    this.studentService = studentService;
    this.courseService = courseService;
    this.teacherRepository = teacherRepository;
  }

  public Iterable<CourseDTO> teacherCourses() {
   SystemUser user =  authz.loggedinUserWithPermissions(ClientRoles.TEACHER).orElseThrow();

   Teacher teacher =  teacherRepository.findByUsername(user.username()).orElseThrow();

   return courseService.listInProgressCoursesThatTeacherLectures(teacher);
  }

  public Iterable<EvaluationExamDTO> courseExams(CourseDTO course) {
    return examService.listAllPastCourseExams(courseService.findCourseByCourseCode(course.getCode()).orElseThrow());
  }
}
