package eapli.ecourse.persistence.impl.inmemory;

import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
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
  public Iterable<Course> findAllOpenForEnrolment() {
    return match(e -> e.enrolmentState().isOpen());
  }

  public Iterable<Course> findAllClosed() {
    return match(e -> e.enrolmentState().isClosed());
  }

  @Override
  public Iterable<Course> findAllOpen() {
    return match(e -> e.state().isOpen());
  }

  @Override
  public Iterable<Course> findAllNotClosed() {
    return match(e -> !e.state().isFinished());
  }
}
