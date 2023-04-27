package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

class JpaCourseRepository
    extends JpaAutoTxRepository<Course, CourseCode, CourseCode>
    implements CourseRepository {

  public JpaCourseRepository(final TransactionalContext autoTx) {
    super(autoTx, "mecanographicNumber");
  }

  public JpaCourseRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "courseCode");
  }

  @Override
  public Optional<Course> findByCode(final CourseCode code) {
    final Map<String, Object> params = new HashMap<>();
    params.put("code", code);
    return matchOne("e.courseCode=:code", params);
  }

  @Override
  public Iterable<Course> coursesOpenedForEnrollment() {
    return match("e.isAcceptingEnrolments = :state", "state", CourseState.ENROLL);
  }

  @Override
  public Iterable<Course> openCourses() {
    return match("e.state = :state", "state", CourseState.OPEN);
  }
}
