package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.domain.EnrolmentID;
import eapli.ecourse.enrolmentmanagement.domain.EnrolmentState;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryEnrolmentRepository
    extends
    InMemoryDomainRepository<Enrolment, EnrolmentID> implements EnrolmentRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<Enrolment> findByStudentMecanographicNumber(
      final MecanographicNumber studentID) {
    return match(e -> e.student().mecanographicNumber().equals(studentID));
  }

  @Override
  public Iterable<Enrolment> findByCourseCode(final CourseCode courseCode) {
    return match(e -> e.course().code().equals(courseCode));
  }

  @Override
  public Iterable<Enrolment> findCourseAccepted(final CourseCode courseCode) {
    return match(e -> e.course().code().equals(courseCode) && e.state().equals(EnrolmentState.ACCEPTED));
  }

  @Override
  public Iterable<Enrolment> findCourseRejected(final CourseCode courseCode) {
    return match(e -> e.course().code().equals(courseCode) && e.state().equals(EnrolmentState.REJECTED));
  }
}
