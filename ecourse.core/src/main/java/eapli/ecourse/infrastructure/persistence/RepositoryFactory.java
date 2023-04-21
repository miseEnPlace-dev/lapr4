package eapli.ecourse.infrastructure.persistence;

import eapli.ecourse.classmanagement.repositories.ClassRepository;
import eapli.ecourse.studentmanagement.repositories.SignupRequestRepository;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * The interface for the repository factory of eCourse.
 * <p>
 * This is the Abstract Factory in the Abstract Factory (GoF) pattern. Each of the return types is
 * an Abstract Product.
 * </p>
 *
 * @author Paulo Gandra Sousa
 */
public interface RepositoryFactory {

  /**
   * Factory method to create a transactional context to use in the repositories
   *
   * @return
   */
  TransactionalContext newTransactionalContext();

  /**
   * @param autoTx the transactional context to enroll
   *
   * @return
   */
  UserRepository users(TransactionalContext autoTx);

  /**
   * repository will be created in auto transaction mode
   *
   * @return
   */
  UserRepository users();

  /**
   * @param autoTx the transactional context to enroll
   *
   * @return
   */
  StudentRepository students(TransactionalContext autoTx);

  /**
   * repository will be created in auto transaction mode
   *
   * @return
   */
  StudentRepository students();

  /**
   * @param autoTx the transactional context to enroll
   *
   * @return
   */
  SignupRequestRepository signupRequests(TransactionalContext autoTx);

  /**
   * repository will be created in auto transaction mode
   *
   * @return
   */
  SignupRequestRepository signupRequests();

  ClassRepository classes();

  ClassRepository classes(TransactionalContext autoTx);
}
