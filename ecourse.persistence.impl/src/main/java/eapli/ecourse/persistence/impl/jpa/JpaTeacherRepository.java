package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecourse.Application;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

class JpaTeacherRepository
    extends JpaAutoTxRepository<Teacher, TaxPayerNumber, TaxPayerNumber>
    implements TeacherRepository {

  public JpaTeacherRepository(final TransactionalContext autoTx) {
    super(autoTx, "taxPayerNumber");
  }

  public JpaTeacherRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "taxPayerNumber");
  }

  @Override
  public Optional<Teacher> findByTaxPayerNumber(final TaxPayerNumber taxPayerNumber) {
    final Map<String, Object> params = new HashMap<>();
    params.put("taxPayerNumber", taxPayerNumber);
    return matchOne("e.taxPayerNumber=:taxPayerNumber", params);
  }
}
