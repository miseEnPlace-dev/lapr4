package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.classmanagement.domain.Class;
import eapli.ecourse.classmanagement.repositories.ClassRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaClassRepository extends JpaAutoTxRepository<Class, Long, Long>
    implements ClassRepository {

  JpaClassRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }


  JpaClassRepository(final String puName) {
    super(puName, "id");
  }

}
