package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

class JpaCourseRepository
    extends JpaAutoTxRepository<Course, CourseCode, CourseCode>
    implements CourseRepository {

  public JpaCourseRepository(final TransactionalContext autoTx) {
    super(autoTx, "code");
  }

  public JpaCourseRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "code");
  }

  @Override
  public Optional<Course> findByCode(final CourseCode code) {
    final Map<String, Object> params = new HashMap<>();
    params.put("code", code);
    return matchOne("e.code=:code", params);
  }

  @Override
  public Iterable<Course> findAllOpenForEnrolment() {
    return match("e.enrolmentState = :enrolmentState", "enrolmentState",
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.OPEN));
  }

  @Override
  public Iterable<Course> findAllOpen() {
    return match("e.courseState = :state", "state", new CourseState(CourseState.State.OPEN));
  }

  public Iterable<Course> findAllClosed() {
    return match("e.courseState = :state", "state", new CourseState(CourseState.State.CLOSED));
  }

  @Override
  public Iterable<Course> findAllInProgress() {
    return match("e.courseState = :state", "state", new CourseState(CourseState.State.IN_PROGRESS));
  }

  @Override
  public Iterable<Course> findAllNotClosed() {
    return match("e.courseState <> :state", "state", new CourseState(CourseState.State.FINISHED));
  }
}
