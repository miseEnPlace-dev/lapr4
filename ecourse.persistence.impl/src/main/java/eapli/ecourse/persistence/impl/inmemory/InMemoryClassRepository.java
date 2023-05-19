package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.classmanagement.domain.Class;
import eapli.ecourse.eventsmanagement.classmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.classmanagement.repositories.ClassRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryClassRepository extends InMemoryDomainRepository<Class, ClassID>
    implements ClassRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<Class> findAllByCourseCode(CourseCode code) {
    return match(e -> e.course().code().equals(code));
  }

  // @Override
  // public Iterable<Class> findAllActive() {
  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method
  // 'findAllActive'");
  // }
}
