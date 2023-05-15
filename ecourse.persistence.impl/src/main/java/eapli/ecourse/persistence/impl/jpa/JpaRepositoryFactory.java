package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.eventsmanagement.classmanagement.repositories.ClassRepository;
import eapli.ecourse.infrastructure.persistence.RepositoryFactory;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.ecourse.studentmanagement.repositories.SignupRequestRepository;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * The repository factory for JPA repositories. This is the concrete factory in
 * the Abstract Factory
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
    return new JpaAutoTxUserRepository(Application.settings().persistenceUnitName(),
        Application.settings().extendedPersistenceProperties());
  }

  @Override
  public JpaStudentRepository students(final TransactionalContext autoTx) {
    return new JpaStudentRepository(autoTx);
  }

  @Override
  public JpaStudentRepository students() {
    return new JpaStudentRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
    return new JpaSignupRequestRepository(autoTx);
  }

  @Override
  public SignupRequestRepository signupRequests() {
    return new JpaSignupRequestRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public TransactionalContext newTransactionalContext() {
    return JpaAutoTxRepository.buildTransactionalContext(
        Application.settings().persistenceUnitName(),
        Application.settings().extendedPersistenceProperties());
  }

  @Override
  public ClassRepository classes() {
    return new JpaClassRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public ClassRepository classes(TransactionalContext autoTx) {
    return new JpaClassRepository(autoTx);
  }

  @Override
  public CourseRepository courses() {
    return new JpaCourseRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public CourseRepository courses(TransactionalContext autoTx) {
    return new JpaCourseRepository(autoTx);
  }

  @Override
  public TeacherRepository teachers() {
    return new JpaTeacherRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public TeacherRepository teachers(TransactionalContext autoTx) {
    return new JpaTeacherRepository(autoTx);
  }

  @Override
  public JpaQuestionRepository questions() {
    return new JpaQuestionRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public QuestionRepository questions(TransactionalContext autoTx) {
    return new JpaQuestionRepository(autoTx);
  }
}
