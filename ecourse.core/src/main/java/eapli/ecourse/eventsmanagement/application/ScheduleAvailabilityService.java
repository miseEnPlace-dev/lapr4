package eapli.ecourse.eventsmanagement.application;

import java.util.ArrayList;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.Hours;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ScheduleAvailabilityService {
  private CourseClassRepository classRepository;
  private MeetingRepository meetingRepository;
  private EnrolmentRepository enrolmentRepository;
  private StudentRepository studentRepository;

  public ScheduleAvailabilityService(CourseClassRepository classRepository,
      MeetingRepository meetingRepository, EnrolmentRepository enrolmentRepository,
      StudentRepository studentRepository) {
    this.classRepository = classRepository;
    this.meetingRepository = meetingRepository;
    this.enrolmentRepository = enrolmentRepository;
    this.studentRepository = studentRepository;
  }

  public boolean areAllAvailable(Iterable<SystemUser> systemUsers, Time time, Duration duration) {
    for (SystemUser systemUser : systemUsers)
      if (!isAvailable(systemUser, time, duration))
        return false;

    return true;
  }

  private boolean isAvailable(SystemUser user, Time time, Duration duration) {
    if (user.hasAny(ClientRoles.STUDENT)) {
      if (!isStudentAvailable(user, time, duration))
        return false;
    }

    return true;
  }

  private boolean isStudentAvailable(SystemUser user, Time time, Duration duration) {
    Student student = studentRepository.findByUsername(user.identity()).orElseThrow();
    Iterable<Enrolment> enrolments = enrolmentRepository.findEnroledCoursesByStudent(student.identity());

    for (Enrolment enrolment : enrolments) {
      Course course = enrolment.course();
      Iterable<CourseClass> classes = classRepository.findAllByCourseCode(course.code());

      if (!isAvailableFromClasses(classes, time, duration))
        return false;

      // TODO - check for extra classes, meetings and special classes
    }

    return true;
  }

  private boolean isAvailableFromClasses(Iterable<CourseClass> classes, Time time, Duration duration) {
    for (CourseClass c : classes) {
      if (!isAvailable(c, time, duration))
        return false;

    }

    return true;
  }

  private boolean isAvailable(CourseClass c, Time scheduleStart, Duration duration) {

    if (c.dayInWeek().equals(scheduleStart.dayInWeek())) {
      Time scheduleEnd = scheduleStart.addDuration(duration);
      Hours classEnd = c.hours().addDuration(c.duration());

      if (overlaps(scheduleStart, classEnd, scheduleEnd, c.hours()))
        return false;

      return false;
    }

    return true;
  }

  private boolean overlaps(Time scheduleStart, Hours classStart, Time scheduleEnd, Hours classEnd) {
    // if scheduleStart is after classEnd and scheduleEnd is before classStart then
    // there is no overlap

    return !(scheduleStart.compareHours(classEnd) > 0 || scheduleEnd.compareHours(classStart) < 0);
  }

}
