package eapli.ecourse.persistence.impl.inmemory;

import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCourseRepository extends
    InMemoryDomainRepository<Course, CourseCode> implements CourseRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Optional<Course> findByCode(final CourseCode code) {
    return Optional.of(data().get(code));
  }

  @Override
  public Iterable<Course> coursesOpenedForEnrollment() {
    return match(e -> e.enrolmentState().equals(CourseEnrolmentState.OPEN));
  }

  @Override
  public Iterable<Course> openCourses() {
    return match(e -> e.state().equals(CourseState.OPEN));
  }

  @Override
  public Iterable<Course> notFinishedCourses() {
    return match(e -> !e.state().equals(CourseState.FINISHED));
  }
}
