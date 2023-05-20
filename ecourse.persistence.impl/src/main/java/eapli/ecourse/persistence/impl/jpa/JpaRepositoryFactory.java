package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.ExtraordinaryClassRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
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
  public CourseClassRepository classes() {
    return new JpaClassRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public CourseClassRepository classes(TransactionalContext autoTx) {
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

  @Override
  public InviteRepository invites(TransactionalContext autoTx) {
    return new JpaInviteRepository(autoTx);
  }

  @Override
  public InviteRepository invites() {
    return new JpaInviteRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public MeetingRepository meetings(TransactionalContext autoTx) {
    return new JpaMeetingRepository(autoTx);
  }

  @Override
  public MeetingRepository meetings() {
    return new JpaMeetingRepository(Application.settings().persistenceUnitName());

  }

  @Override
  public EvaluationExamRepository evaluationExams() {
    return new JpaExamRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public EvaluationExamRepository evaluationExams(TransactionalContext autoTx) {
    return new JpaExamRepository(autoTx);
  }

  @Override
  public JpaEnrolmentRepository enrollments() {
    return new JpaEnrolmentRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public JpaEnrolmentRepository enrollments(TransactionalContext autoTx) {
    return new JpaEnrolmentRepository(autoTx);
  }

  @Override
  public BoardRepository boards() {
    return new JpaBoardRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public BoardRepository boards(TransactionalContext autoTx) {
    return new JpaBoardRepository(autoTx);
  }

  @Override
  public ExtraordinaryClassRepository extraordinaryClasses() {
    return new JpaExtraordinaryClassRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public ExtraordinaryClassRepository extraordinaryClasses(TransactionalContext autoTx) {
    return new JpaExtraordinaryClassRepository(autoTx);
  }

  @Override
  public FormativeExamRepository formativeExams() {
    return new JpaFormativeExamRepository(Application.settings().persistenceUnitName());
  }

  @Override
  public FormativeExamRepository formativeExams(TransactionalContext autoTx) {
    return new JpaFormativeExamRepository(autoTx);
  }
}
