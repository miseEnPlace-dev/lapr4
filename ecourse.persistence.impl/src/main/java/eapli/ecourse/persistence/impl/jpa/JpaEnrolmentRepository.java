package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.domain.EnrolmentID;
import eapli.ecourse.enrolmentmanagement.domain.EnrolmentState;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaEnrolmentRepository extends JpaAutoTxRepository<Enrolment, EnrolmentID, EnrolmentID>
    implements EnrolmentRepository {

  public JpaEnrolmentRepository(final TransactionalContext autoTx) {
    super(autoTx, "enrolmentID");
  }

  public JpaEnrolmentRepository(final String puname) {
    super(puname, "enrolmentID");
  }

  @Override
  public Iterable<Enrolment> findByStudentMecanographicNumber(
      final MecanographicNumber studentID) {
    return match("e.student.mecanographicNumber = :studentID", "studentID", studentID);
  }

  @Override
  public Iterable<Enrolment> findByCourseCode(final CourseCode courseCode) {
    return match("e.course.code = :courseCode", "courseCode", courseCode);
  }

  @Override
  public Iterable<Enrolment> findCourseAccepted(final CourseCode courseCode) {
    return match("e.course.code = :courseCode AND e.state = :state", "courseCode", courseCode,
        "state", EnrolmentState.ACCEPTED);
  }

  @Override
  public Iterable<Enrolment> findCourseRejected(final CourseCode courseCode) {
    return match("e.course.code = :courseCode AND e.state = :state", "courseCode", courseCode,
        "state", EnrolmentState.REJECTED);
  }
}
