package eapli.ecourse.eventsmanagement.classmanagement.repositories;

import eapli.ecourse.eventsmanagement.classmanagement.domain.Class;
import eapli.framework.domain.repositories.DomainRepository;

public interface ClassRepository extends DomainRepository<Long, Class> {
  // Iterable<Class> findAllActive();
}
