package eapli.ecourse.persistence.impl.inmemory;

import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCourseRepository extends
    InMemoryDomainRepository<Course, CourseCode> implements CourseRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<Course> findAllOpenForEnrolment() {
    return match(e -> e.enrolmentState().isOpen());
  }

  public Iterable<Course> findAllClosed() {
    return match(e -> e.state().isClosed());
  }

  @Override
  public Iterable<Course> findAllOpen() {
    return match(e -> e.state().isOpen());
  }

  @Override
  public Iterable<Course> findAllNotClosed() {
    return match(e -> !e.state().isClosed());
  }

  @Override
  public Iterable<Course> findAllInProgress() {
    return match(e -> e.state().isInProgress());
  }

  @Override
  public Iterable<Course> findAllInProgressLecturedByTeacher(Teacher teacher) {
    return match(
        e -> e.state().isInProgress() && (e.teachers().contains(teacher) | e.teacherInCharge().equals(teacher)));
  }

  @Override
  public Iterable<Course> findNotClosedCoursesThatTeacherLectures(Teacher teacher) {
    return match(e -> !e.state().isClosed() && (e.teachers().contains(teacher) | e.teacherInCharge().equals(teacher)));
  }

  @Override
  public Iterable<Course> findAllInProgressThatStudentIsEnrolled(Student student) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Iterable<Course> findAllNotFinished() {
    return match(e -> !e.state().isFinished());
  }

  @Override
  public Optional<Course> findByCode(CourseCode code) {
    return ofIdentity(code);
  }
}
