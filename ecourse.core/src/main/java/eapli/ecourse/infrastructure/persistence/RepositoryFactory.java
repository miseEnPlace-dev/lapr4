package eapli.ecourse.infrastructure.persistence;

import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.ecourse.studentmanagement.repositories.SignupRequestRepository;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * The interface for the repository factory of eCourse.
 * <p>
 * This is the Abstract Factory in the Abstract Factory (GoF) pattern. Each of
 * the return types is
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

  /**
   * repository will be created in auto transaction mode
   *
   * @return
   */
  CourseClassRepository classes();

  CourseClassRepository classes(TransactionalContext autoTx);

  /**
   * repository will be created in auto transaction mode
   *
   * @return
   */
  CourseRepository courses();

  CourseRepository courses(TransactionalContext autoTx);

  /**
   * repository will be created in auto transaction mode
   *
   * @return
   */
  TeacherRepository teachers();

  TeacherRepository teachers(TransactionalContext autoTx);

  /**
   * repository will be created in auto transaction mode
   *
   * @return
   */
  QuestionRepository questions();

  QuestionRepository questions(TransactionalContext autoTx);

  /**
   *
   * repository will be created in auto transaction mode
   *
   * @return
   */
  MeetingRepository meetings();

  MeetingRepository meetings(TransactionalContext autoTx);

  /**
   *
   * repository will be created in auto transaction mode
   *
   * @return
   */
  InviteRepository invites();

  InviteRepository invites(TransactionalContext autoTx);

  /**
   *
   * repository will be created in auto transaction mode
   *
   * @return
   */
  ExamRepository exams();

  ExamRepository exams(TransactionalContext autoTx);

  /**
   *
   * repository will be created in auto transaction mode
   *
   * @return
   */
  EnrolmentRepository enrollments();

  EnrolmentRepository enrollments(TransactionalContext autoTx);

  /**
   *
   * repository will be created in auto transaction mode
   *
   * @return
   */
  BoardRepository boards();

  BoardRepository boards(TransactionalContext autoTx);
}
