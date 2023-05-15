package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.eventsmanagement.classmanagement.domain.Class;
import eapli.ecourse.eventsmanagement.classmanagement.repositories.ClassRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryClassRepository extends InMemoryDomainRepository<Class, Long>
    implements ClassRepository {

  static {
    InMemoryInitializer.init();
  }

  // @Override
  // public Iterable<Class> findAllActive() {
  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method
  // 'findAllActive'");
  // }
}
