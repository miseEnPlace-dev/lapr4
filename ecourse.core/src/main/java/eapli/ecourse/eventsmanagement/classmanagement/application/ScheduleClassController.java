package eapli.ecourse.eventsmanagement.classmanagement.application;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.DayInWeek;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.Hours;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ScheduleClassController {
  private ListCourseService listCourseService;

  private Teacher teacher;

  private CourseClassRepository classRepository;
  private CourseRepository courseRepository;
  private TeacherRepository teacherRepository;

  public ScheduleClassController(CourseClassRepository classRepository,
      CourseRepository courseRepository, TeacherRepository teacherRepository) {
    this.listCourseService = new ListCourseService(courseRepository);

    this.classRepository = classRepository;
    this.courseRepository = courseRepository;
    this.teacherRepository = teacherRepository;
  }

  public void setCurrentAuthenticatedTeacher() {
    AuthorizationService authz = AuthzRegistry.authorizationService();

    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.TEACHER);
    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.TEACHER).orElseThrow();

    teacher = teacherRepository.findByUsername(authenticatedUser.username()).orElseThrow();
  }

  public Iterable<CourseDTO> listAllInProgressLecturedBy() {
    setCurrentAuthenticatedTeacher();

    return listCourseService.listInProgressCoursesThatTeacherLectures(teacher);
  }

  public CourseClass createClass(CourseCode code, int duration, DayInWeek day, Hours hours) {
    Course course = courseRepository.ofIdentity(code).orElseThrow();

    Duration durationObj = Duration.valueOf(duration);

    return classRepository.save(new CourseClass(day, durationObj, hours, course, teacher));
  }
}
