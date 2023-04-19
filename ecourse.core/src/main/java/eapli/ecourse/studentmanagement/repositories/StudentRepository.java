package eapli.ecourse.studentmanagement.repositories;

import java.util.Optional;

import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface StudentRepository extends DomainRepository<MecanographicNumber, Student> {

  /**
   * Returns the client user (utente) whose username is given.
   *
   * @param name the username to search for
   * @return
   */
  Optional<Student> findByUsername(Username name);

  /**
   * Returns the client user (utente) with the given mecanographic number.
   *
   * @param number
   * @return
   */
  default Optional<Student> findByMecanographicNumber(final MecanographicNumber number) {
    return ofIdentity(number);
  }

  /**
   *
   * @return
   */
  Iterable<Student> findAllActive();
}
