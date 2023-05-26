package eapli.ecourse.persistence.impl.jpa;

import javax.persistence.TypedQuery;

import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.ExtraordinaryClassRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
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

  @Override
  public Iterable<ExtraordinaryClass> findAllByStudentMecanographicNumber(MecanographicNumber mecanographicNumber) {
    final TypedQuery<ExtraordinaryClass> query = entityManager().createQuery(
        "SELECT ec FROM ExtraordinaryClass ec JOIN ec.students s WHERE s.mecanographicNumber = :mecanographicNumber",
        ExtraordinaryClass.class);

    query.setParameter("mecanographicNumber", mecanographicNumber);

    return query.getResultList();
  }

}
