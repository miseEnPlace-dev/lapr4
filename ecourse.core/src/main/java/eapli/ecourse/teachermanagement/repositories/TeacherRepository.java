package eapli.ecourse.teachermanagement.repositories;

import java.util.Optional;

import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface TeacherRepository extends DomainRepository<TaxPayerNumber, Teacher> {
  /**
   * Returns the teacher with the given username.
   *
   * @param name
   * @return
   */
  Optional<Teacher> findByUsername(final Username name);

  /**
   * Returns the teacher with the given tax payer number.
   *
   * @param taxPayerNumber
   * @return
   */
  default Optional<Teacher> findByTaxPayerNumber(final TaxPayerNumber taxPayerNumber) {
    return ofIdentity(taxPayerNumber);
  }

  /**
   * Returns the teachers registered
   *
   * @return
   */
  Iterable<Teacher> findAll();

}
