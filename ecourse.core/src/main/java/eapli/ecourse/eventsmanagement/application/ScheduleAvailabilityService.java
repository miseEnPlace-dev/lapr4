package eapli.ecourse.eventsmanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.Hours;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.SpecialClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.ExtraordinaryClassRepository;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ScheduleAvailabilityService {
  private CourseClassRepository classRepository;
  private ExtraordinaryClassRepository extraordinaryClassRepository;
  private InviteRepository inviteRepository;
  private EnrolmentRepository enrolmentRepository;
  private StudentRepository studentRepository;
  private TeacherRepository teacherRepository;

  public ScheduleAvailabilityService(CourseClassRepository classRepository,
      ExtraordinaryClassRepository extraordinaryClassRepository,
      InviteRepository inviteRepository, EnrolmentRepository enrolmentRepository,
      StudentRepository studentRepository, TeacherRepository teacherRepository) {
    this.classRepository = classRepository;
    this.extraordinaryClassRepository = extraordinaryClassRepository;
    this.inviteRepository = inviteRepository;
    this.enrolmentRepository = enrolmentRepository;
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
  }

  public boolean areAllAvailable(Iterable<SystemUser> systemUsers, Time time, Duration duration) {
    for (SystemUser systemUser : systemUsers)
      if (!isAvailable(systemUser, time, duration))
        return false;

    return true;
  }

  private boolean isAvailable(SystemUser user, Time time, Duration duration) {
    Iterable<Invite> userMeetingInvites = inviteRepository.findAllAcceptedForUsername(user.username());
    if (!isAvailableFromMeetings(userMeetingInvites, duration, time))
      return false;

    if (user.hasAny(ClientRoles.STUDENT)) {
      Student student = studentRepository.findByUsername(user.identity()).orElseThrow();
      if (!isStudentAvailable(student, duration, time))
        return false;
    }

    if (user.hasAny(ClientRoles.TEACHER)) {
      Teacher teacher = teacherRepository.findByUsername(user.identity()).orElseThrow();
      if (!isTeacherAvailable(teacher, duration, time))
        return false;
    }

    return true;
  }

  private boolean isTeacherAvailable(Teacher teacher, Duration duration, Time time) {
    final Iterable<CourseClass> teacherClasses = classRepository
        .findAllScheduledByTeacherTaxPayerNumber(teacher.taxPayerNumber());
    for (CourseClass cl : teacherClasses) {
      if (!isTeacherAvailableFromClass(cl, time, duration))
        return false;
      if (!isTeacherAvailableFromSpecialClasses(cl.specialClasses(), cl.duration(), time, duration))
        return false;
    }

    return true;
  }

  private boolean isTeacherAvailableFromClass(CourseClass cl, Time time, Duration duration) {
    if (cl.dayInWeek().equals(time.dayInWeek())) {
      Time scheduleEnd = time.addDuration(duration);
      Hours classEnd = cl.hours().addDuration(cl.duration());

      if (overlaps(time, cl.hours(), scheduleEnd, classEnd))
        return false;
    }

    return true;
  }

  private boolean isTeacherAvailableFromSpecialClasses(Iterable<SpecialClass> specialClasses, Duration duration,
      Time time, Duration classDuration) {
    for (SpecialClass specialClass : specialClasses) {
      if (!isTeacherAvailableFromSpecialClass(specialClass, duration, time, classDuration))
        return false;
    }

    return true;
  }

  private boolean isTeacherAvailableFromSpecialClass(SpecialClass specialClass, Duration duration, Time time,
      Duration classDuration) {
    if (specialClass.time().dayInWeek().equals(time.dayInWeek())) {
      Time scheduleEnd = time.addDuration(duration);
      Time classEnd = specialClass.time().addDuration(classDuration);

      if (overlaps(time, specialClass.time(), scheduleEnd, classEnd))
        return false;
    }

    return true;
  }

  private boolean isStudentAvailable(Student student, Duration duration, Time time) {
    Iterable<Enrolment> enrollments = enrolmentRepository.findStudentCourses(student.identity());

    for (Enrolment enrolment : enrollments) {
      Course course = enrolment.course();
      Iterable<CourseClass> classes = classRepository.findAllByCourseCode(course.code());
      Iterable<ExtraordinaryClass> extraClasses = extraordinaryClassRepository
          .findAllByStudentMecanographicNumber(student.mecanographicNumber());

      if (!isStudentAvailableFromClasses(classes, extraClasses, duration, time))
        return false;
    }

    return true;
  }

  private boolean isStudentAvailableFromClasses(Iterable<CourseClass> classes,
      Iterable<ExtraordinaryClass> extraClasses, Duration duration, Time time) {
    for (CourseClass cl : classes) {
      if (!isStudentAvailableFromClass(cl, time, duration))
        return false;
      if (!isStudentAvailableFromSpecialClasses(cl.specialClasses(), cl.duration(), time, duration))
        return false;
      if (!isStudentAvailableFromExtraClasses(extraClasses, duration, time))
        return false;
    }

    return true;
  }

  private boolean isAvailableFromMeetings(Iterable<Invite> meetingInvites, Duration duration, Time time) {
    for (Invite invite : meetingInvites)
      if (!isAvailableFromMeeting(invite.meeting(), duration, time))
        return false;

    return true;
  }

  private boolean isAvailableFromMeeting(Meeting meeting, Duration duration, Time time) {
    if (meeting.time().dayInWeek().equals(time.dayInWeek())) {
      Time scheduleEnd = time.addDuration(duration);
      Time meetingEnd = meeting.time().addDuration(meeting.duration());

      if (overlaps(time, meeting.time(), scheduleEnd, meetingEnd))
        return false;
    }

    return true;
  }

  private boolean isStudentAvailableFromExtraClasses(Iterable<ExtraordinaryClass> extraClasses, Duration duration,
      Time time) {
    for (ExtraordinaryClass extraClass : extraClasses)
      if (!isAvailableFromExtraClass(extraClass, duration, time))
        return false;

    return true;
  }

  private boolean isAvailableFromExtraClass(ExtraordinaryClass extraClass, Duration duration, Time time) {
    if (extraClass.time().dayInWeek().equals(time.dayInWeek())) {
      Time scheduleEnd = time.addDuration(duration);
      Time classEnd = extraClass.time().addDuration(extraClass.duration());

      if (overlaps(time, extraClass.time(), scheduleEnd, classEnd))
        return false;
    }

    return true;
  }

  private boolean isStudentAvailableFromSpecialClasses(Iterable<SpecialClass> specialClasses, Duration classDuration,
      Time time, Duration duration) {
    for (SpecialClass specialClass : specialClasses) {
      if (specialClass.time().dayInWeek().equals(time.dayInWeek())) {
        Time specialClassStart = specialClass.time();
        Time specialClassEnd = specialClassStart.addDuration(classDuration);

        if (overlaps(time, specialClassStart, time.addDuration(duration), specialClassEnd))
          return false;
      }
    }

    return true;
  }

  private boolean isStudentAvailableFromClass(CourseClass cl, Time scheduleStart, Duration duration) {
    if (cl.dayInWeek().equals(scheduleStart.dayInWeek())) {
      Time scheduleEnd = scheduleStart.addDuration(duration);
      Hours classEnd = cl.hours().addDuration(cl.duration());

      if (overlaps(scheduleStart, cl.hours(), scheduleEnd, classEnd))
        return false;
    }

    return true;
  }

  private boolean overlaps(Time scheduleStart, Hours classStart, Time scheduleEnd, Hours classEnd) {
    // if scheduleStart is after classEnd and scheduleEnd is before classStart then
    // there is no overlap

    return !(scheduleStart.compareHours(classEnd) > 0 || scheduleEnd.compareHours(classStart) < 0);
  }

  private boolean overlaps(Time scheduleStart, Time classStart, Time scheduleEnd, Time classEnd) {
    // if scheduleStart is after classEnd and scheduleEnd is before classStart then
    // there is no overlap

    return !(scheduleStart.compareTo(classEnd) > 0 || scheduleEnd.compareTo(classStart) < 0);
  }
}
