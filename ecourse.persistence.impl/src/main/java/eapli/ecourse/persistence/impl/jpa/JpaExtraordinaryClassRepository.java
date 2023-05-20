package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.ExtraordinaryClassRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaExtraordinaryClassRepository
    extends JpaAutoTxRepository<ExtraordinaryClass, ExtraordinaryClassID, ExtraordinaryClassID>
    implements ExtraordinaryClassRepository {

  JpaExtraordinaryClassRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  JpaExtraordinaryClassRepository(final String puName) {
    super(puName, "id");
  }
}
