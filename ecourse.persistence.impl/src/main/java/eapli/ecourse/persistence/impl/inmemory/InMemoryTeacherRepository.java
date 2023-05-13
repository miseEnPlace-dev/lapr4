package eapli.ecourse.persistence.impl.inmemory;

import java.util.Optional;

import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryTeacherRepository extends
    InMemoryDomainRepository<Teacher, TaxPayerNumber> implements TeacherRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Optional<Teacher> findByTaxPayerNumber(final TaxPayerNumber number) {
    return Optional.of(data().get(number));
  }
}
