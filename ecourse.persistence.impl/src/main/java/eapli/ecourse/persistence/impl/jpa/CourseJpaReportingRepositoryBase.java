package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalContext;

/**
 * A base class for all reporting repositories to use the same persistence unit.
 *
 * @param <T>
 * @param <K>
 *
 * @author Paulo Gandra de Sousa
 */
/* package */ class CourseJpaReportingRepositoryBase extends JpaTransactionalContext {

  CourseJpaReportingRepositoryBase() {
    super(Application.settings().persistenceUnitName(),
        Application.settings().extendedPersistenceProperties());
  }

  CourseJpaReportingRepositoryBase(final String persistenceUnitName) {
    super(persistenceUnitName, Application.settings().extendedPersistenceProperties());
  }
}
