package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.eventsmanagement.classmanagement.domain.Class;
import eapli.ecourse.eventsmanagement.classmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.classmanagement.repositories.ClassRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaClassRepository extends JpaAutoTxRepository<Class, ClassID, ClassID>
    implements ClassRepository {

  JpaClassRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  JpaClassRepository(final String puName) {
    super(puName, "id");
  }

}
