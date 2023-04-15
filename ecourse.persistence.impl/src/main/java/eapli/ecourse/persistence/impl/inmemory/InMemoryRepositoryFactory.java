package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.clientusermanagement.repositories.ClientUserRepository;
import eapli.ecourse.clientusermanagement.repositories.SignupRequestRepository;
import eapli.ecourse.infrastructure.bootstrapers.ECourseBootstrapper;
import eapli.ecourse.infrastructure.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

/**
 *
 * @author Nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

  /**
   * Initialize a power user so that we can login.
   */
  @Override
  public UserRepository users(final TransactionalContext tx) {
    final InMemoryUserRepository repo = new InMemoryUserRepository();
    ECourseBootstrapper.registerPowerUser(repo);
    return repo;
  }

  @Override
  public UserRepository users() {
    return users(null);
  }

  @Override
  public ClientUserRepository clientUsers(final TransactionalContext tx) {
    return new InMemoryClientUserRepository();
  }

  @Override
  public ClientUserRepository clientUsers() {
    return clientUsers(null);
  }

  @Override
  public SignupRequestRepository signupRequests() {
    return signupRequests(null);
  }

  @Override
  public SignupRequestRepository signupRequests(final TransactionalContext tx) {
    return new InMemorySignupRequestRepository();
  }

  @Override
  public TransactionalContext newTransactionalContext() {
    // in memory does not support transactions
    return null;
  }

}