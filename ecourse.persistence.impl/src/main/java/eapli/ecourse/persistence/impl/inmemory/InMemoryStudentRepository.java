package eapli.ecourse.persistence.impl.inmemory;

import java.util.Optional;

import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class InMemoryStudentRepository extends
    InMemoryDomainRepository<Student, MecanographicNumber> implements StudentRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Optional<Student> findByUsername(final Username name) {
    return matchOne(e -> e.user().username().equals(name));
  }

  @Override
  public Optional<Student> findByMecanographicNumber(final MecanographicNumber number) {
    return Optional.of(data().get(number));
  }

  @Override
  public Iterable<Student> findAllActive() {
    return match(e -> e.user().isActive());
  }
}
