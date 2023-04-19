package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecourse.Application;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaStudentRepository
    extends JpaAutoTxRepository<Student, MecanographicNumber, MecanographicNumber>
    implements StudentRepository {

  public JpaStudentRepository(final TransactionalContext autoTx) {
    super(autoTx, "mecanographicNumber");
  }

  public JpaStudentRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "mecanographicNumber");
  }

  @Override
  public Optional<Student> findByUsername(final Username name) {
    final Map<String, Object> params = new HashMap<>();
    params.put("name", name);
    return matchOne("e.systemUser.username=:name", params);
  }

  @Override
  public Optional<Student> findByMecanographicNumber(final MecanographicNumber number) {
    final Map<String, Object> params = new HashMap<>();
    params.put("number", number);
    return matchOne("e.mecanographicNumber=:number", params);
  }

  @Override
  public Iterable<Student> findAllActive() {
    return match("e.systemUser.active = true");
  }
}
