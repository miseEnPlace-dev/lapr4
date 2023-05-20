package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    super(autoTx, "id");
  }

  public JpaEnrolmentRepository(final String puname) {
    super(puname, "id");
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
  public Iterable<Enrolment> findPendingByCourseCode(final CourseCode courseCode) {
    return match("e.course.code = :courseCode AND e.state = :state", "courseCode", courseCode,
        "state", new EnrolmentState(EnrolmentState.State.PENDING));
  }

  @Override
  public Iterable<Enrolment> findCourseAccepted(final CourseCode courseCode) {
    return match("e.course.code = :courseCode AND e.state = :state", "courseCode", courseCode,
        "state", new EnrolmentState(EnrolmentState.State.ACCEPTED));
  }

  @Override
  public Iterable<Enrolment> findCourseRejected(final CourseCode courseCode) {
    return match("e.course.code = :courseCode AND e.state = :state", "courseCode", courseCode,
        "state", new EnrolmentState(EnrolmentState.State.REJECTED));
  }

  @Override
  public Optional<Enrolment> findWithUserAndCourse(final MecanographicNumber studentID,
      final CourseCode courseCode) {
    Map<String, Object> params = new HashMap<>();
    params.put("studentID", studentID);
    params.put("courseCode", courseCode);

    return matchOne("e.student.mecanographicNumber = :studentID AND e.course.code = :courseCode",
        params);
  }

  @Override
  public Iterable<Enrolment> findStudentCourses(MecanographicNumber studentID) {
    Map<String, Object> params = new HashMap<>();
    params.put("studentID", studentID);
    params.put("state", new EnrolmentState(EnrolmentState.State.ACCEPTED));

    return match("e.student.mecanographicNumber = :studentID AND e.state = :state",
        params);
  }

  @Override
  public Iterable<Enrolment> findStudentsEnrolledInCourse(CourseCode code) {
    Map<String, Object> params = new HashMap<>();
    params.put("courseCode", code);
    params.put("state", new EnrolmentState(EnrolmentState.State.ACCEPTED));

    return match("e.course.code = :courseCode AND e.state = :state",
        params);
  }
}
