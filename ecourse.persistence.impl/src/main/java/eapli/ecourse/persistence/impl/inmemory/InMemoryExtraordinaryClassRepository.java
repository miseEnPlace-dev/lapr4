package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.ExtraordinaryClassRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryExtraordinaryClassRepository
    extends InMemoryDomainRepository<ExtraordinaryClass, ExtraordinaryClassID>
    implements ExtraordinaryClassRepository {

  static {
    InMemoryInitializer.init();
  }
}
