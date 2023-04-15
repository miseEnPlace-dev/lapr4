package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalRepository;

/**
 * A base class for all transactional repositories to use the same persistence unit.
 *
 * @param <T>
 * @param <K>
 *
 * @author Paulo Gandra de Sousa
 */
/* package */ class CourseJpaRepositoryBase<T, K, I> extends JpaTransactionalRepository<T, K, I> {

  CourseJpaRepositoryBase(final String persistenceUnitName, final String identityFieldName) {
    super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(),
        identityFieldName);
  }

  CourseJpaRepositoryBase(final String identityFieldName) {
    super(Application.settings().getPersistenceUnitName(),
        Application.settings().getExtendedPersistenceProperties(), identityFieldName);
  }
}
