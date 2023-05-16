package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.eventsmanagement.classmanagement.repositories.ClassRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;
import eapli.ecourse.infrastructure.bootstrapers.ECourseBootstrapper;
import eapli.ecourse.infrastructure.persistence.RepositoryFactory;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.ecourse.studentmanagement.repositories.SignupRequestRepository;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
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
  public StudentRepository students(final TransactionalContext tx) {
    return new InMemoryStudentRepository();
  }

  @Override
  public StudentRepository students() {
    return students(null);
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

  @Override
  public ClassRepository classes() {
    return classes(null);
  }

  @Override
  public ClassRepository classes(final TransactionalContext autoTx) {
    return new InMemoryClassRepository();
  }

  @Override
  public CourseRepository courses() {
    return courses(null);
  }

  @Override
  public CourseRepository courses(final TransactionalContext autoTx) {
    return new InMemoryCourseRepository();
  }

  @Override
  public TeacherRepository teachers() {
    return teachers(null);
  }

  @Override
  public TeacherRepository teachers(final TransactionalContext autoTx) {
    return new InMemoryTeacherRepository();
  }

  @Override
  public QuestionRepository questions() {
    return questions(null);
  }

  @Override
  public QuestionRepository questions(final TransactionalContext autoTx) {
    return new InMemoryQuestionRepository();
  }

  @Override
  public InviteRepository invites() {
    return invites(null);
  }

  @Override
  public InviteRepository invites(final TransactionalContext autoTx) {
    return new InMemoryInviteRepository();
  }

  @Override
  public MeetingRepository meetings() {
    return meetings(null);
  }

  @Override
  public MeetingRepository meetings(final TransactionalContext autoTx) {
    return new InMemoryMeetingRepository();
  }

  @Override
  public ExamRepository exams() {
    return exams(null);
  }

  @Override
  public ExamRepository exams(final TransactionalContext autoTx) {
    return new InMemoryExamRepository();
  }

  @Override
  public EnrolmentRepository enrollments() {
    return enrollments(null);
  }

  @Override
  public EnrolmentRepository enrollments(final TransactionalContext autoTx) {
    return new InMemoryEnrolmentRepository();
  }
}
