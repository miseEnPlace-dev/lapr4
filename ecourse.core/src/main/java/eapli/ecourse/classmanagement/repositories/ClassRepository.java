package eapli.ecourse.classmanagement.repositories;

import eapli.ecourse.classmanagement.domain.Class;
import eapli.framework.domain.repositories.DomainRepository;

public interface ClassRepository extends DomainRepository<Long, Class> {
  // Iterable<Class> findAllActive();
}
