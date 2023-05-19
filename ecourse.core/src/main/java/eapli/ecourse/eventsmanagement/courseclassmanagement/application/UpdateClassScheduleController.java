package eapli.ecourse.eventsmanagement.courseclassmanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.eventsmanagement.application.ScheduleAvailabilityService;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.DayInWeek;
import eapli.ecourse.eventsmanagement.courseclassmanagement.dto.ClassDTO;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class UpdateClassScheduleController {
  private CourseClassRepository classRepository;
  private ScheduleAvailabilityService scheduleAvailabilityService;
  private AuthorizationService authzRegistry;

  public UpdateClassScheduleController(final CourseClassRepository classRepository,
      final AuthorizationService authzRegistry,
      MeetingRepository meetingRepository, EnrolmentRepository enrolmentRepository,
      StudentRepository studentRepository) {
    this.classRepository = classRepository;
    this.scheduleAvailabilityService = new ScheduleAvailabilityService(classRepository, meetingRepository,
        enrolmentRepository, studentRepository);
    this.authzRegistry = authzRegistry;
  }

  public CourseClass updateScheduleClass(DayInWeek dayInWeek, Time time, Course course) {
    authzRegistry.ensureAuthenticatedUserHasAnyOf(ClientRoles.TEACHER);

    Preconditions.noneNull(dayInWeek, time, course);

    return saveClass(null);

  }

  private CourseClass saveClass(CourseClass newClass) {
    return classRepository.save(newClass);
  }

  public Iterable<ClassDTO> listAllClassesForTeacher() {
    // return classRepository.findAllByTeacher();
    // TODO
    return null;
  }
}
