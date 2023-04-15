package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.clientusermanagement.repositories.SignupRequestRepository;
import eapli.ecourse.infrastructure.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * The repository factory for JPA repositories. This is the concrete factory in the Abstract Factory
 * (GoF) pattern.
 *
 * @author Nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

  @Override
  public UserRepository users(final TransactionalContext autoTx) {
    return new JpaAutoTxUserRepository(autoTx);
  }

  @Override
  public UserRepository users() {
    return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
        Application.settings().getExtendedPersistenceProperties());
  }

  @Override
  public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
    return new JpaClientUserRepository(autoTx);
  }

  @Override
  public JpaClientUserRepository clientUsers() {
    return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
  }

  @Override
  public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
    return new JpaSignupRequestRepository(autoTx);
  }

  @Override
  public SignupRequestRepository signupRequests() {
    return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
  }

  @Override
  public TransactionalContext newTransactionalContext() {
    return JpaAutoTxRepository.buildTransactionalContext(
        Application.settings().getPersistenceUnitName(),
        Application.settings().getExtendedPersistenceProperties());
  }

}
