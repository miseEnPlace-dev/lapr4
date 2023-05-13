package eapli.ecourse.teachermanagement.repositories;

import java.util.Optional;

import eapli.ecourse.studentmanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * TODO: not completed yet
 *
 */
public interface TeacherRepository extends DomainRepository<TaxPayerNumber, Teacher> {
  /**
   * Returns the course with the given code.
   *
   * @param taxPayerNumber
   * @return
   */
  default Optional<Teacher> findByTaxPayerNumber(final TaxPayerNumber taxPayerNumber) {
    return ofIdentity(taxPayerNumber);
  }

  /**
   * Returns the courses that are open
   *
   * @return
   */
  Iterable<Teacher> findAll();

}
