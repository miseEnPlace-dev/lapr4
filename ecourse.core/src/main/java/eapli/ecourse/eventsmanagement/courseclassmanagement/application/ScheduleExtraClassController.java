package eapli.ecourse.eventsmanagement.courseclassmanagement.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.application.ListEnrolmentService;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.eventsmanagement.application.ScheduleAvailabilityService;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.ExtraordinaryClassRepository;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.dto.StudentDTO;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ScheduleExtraClassController {

  private ListEnrolmentService enrolmentListService;
  private ListCourseService listCourseService;
  private ScheduleAvailabilityService scheduleAvailabilityService;

  private Teacher teacher;

  private CourseRepository courseRepository;
  private ExtraordinaryClassRepository extraClassRepository;
  private TeacherRepository teacherRepository;
  private StudentRepository studentRepository;

  public ScheduleExtraClassController(CourseRepository courseRepository, EnrolmentRepository enrolmentRepository,
      ExtraordinaryClassRepository extraClassRepository, InviteRepository inviteRepository,
      StudentRepository studentRepository, TeacherRepository teacherRepository, CourseClassRepository classRepository) {
    this.enrolmentListService = new ListEnrolmentService(enrolmentRepository);
    this.listCourseService = new ListCourseService(courseRepository);
    this.scheduleAvailabilityService = new ScheduleAvailabilityService(classRepository, extraClassRepository,
        inviteRepository, enrolmentRepository, studentRepository, teacherRepository);

    this.courseRepository = courseRepository;
    this.extraClassRepository = extraClassRepository;
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;

  }

  public Iterable<CourseDTO> listAllInProgressLecturedBy() {
    setCurrentAuthenticatedTeacher();

    return listCourseService.listInProgressCoursesThatTeacherLectures(teacher);
  }

  public void setCurrentAuthenticatedTeacher() {
    AuthorizationService authz = AuthzRegistry.authorizationService();

    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.TEACHER, ClientRoles.POWER_USER);

    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.TEACHER).orElseThrow();

    teacher = teacherRepository.findByUsername(authenticatedUser.username()).orElseThrow();
  }

  public Iterable<StudentDTO> listStudentsEnrolled(CourseDTO courseDTO) {
    Iterable<EnrolmentDTO> enrolments = enrolmentListService.listStudentsEnrolled(courseDTO.getCode());
    Set<StudentDTO> students = new HashSet<>();
    for (EnrolmentDTO enrolment : enrolments)
      students.add(studentRepository.ofIdentity(enrolment.getStudentNumber()).orElseThrow().toDto());

    return students;
  }

  public ExtraordinaryClass createExtraordinaryClass(CourseCode code, int duration, Calendar time,
      Iterable<StudentDTO> students) {

    Course course = courseRepository.ofIdentity(code).orElseThrow();
    Set<Student> studentsSet = new HashSet<>();

    List<SystemUser> users = new ArrayList<>();

    for (StudentDTO student : students) {
      studentsSet.add(studentRepository.ofIdentity(student.getMecanographicNumber()).orElseThrow());
      users.add(studentRepository.ofIdentity(student.getMecanographicNumber()).orElseThrow().user());
    }
    users.add(teacher.user());

    Duration durationObj = Duration.valueOf(duration);

    Time timeObj = Time.valueOf(time);

    if (!scheduleAvailabilityService.areAllAvailable(users, timeObj,
        durationObj))
      throw new IllegalArgumentException("The schedule is not available");

    ExtraordinaryClass extraClass = new ExtraordinaryClass(durationObj, timeObj, this.teacher, studentsSet, course);

    return saveExtraordinaryClass(extraClass);
  }

  private ExtraordinaryClass saveExtraordinaryClass(ExtraordinaryClass extraClass) {
    return extraClassRepository.save(extraClass);
  }

}
