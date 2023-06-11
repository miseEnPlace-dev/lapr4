package eapli.ecourse.persistence.impl.jpa;

import javax.persistence.TypedQuery;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaFormativeExamRepository extends JpaAutoTxRepository<FormativeExam, ExamIdentifier, ExamIdentifier>
    implements FormativeExamRepository {
  public JpaFormativeExamRepository(final TransactionalContext autoTx) {
    super(autoTx, "identifier");
  }

  public JpaFormativeExamRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "identifier");
  }

  @Override
  public Iterable<FormativeExam> findAllCourseExams(Course course) {
    return match("e.course = :course", "course", course);
  }

  @Override
  public Iterable<FormativeExam> findAllCourseExamsWithNoAnswersFromStudent(CourseCode code,
      MecanographicNumber number) {
    final TypedQuery<FormativeExam> query = entityManager().createQuery(
        "SELECT e FROM FormativeExam e WHERE e NOT IN (SELECT a.exam FROM ExamAnswer a WHERE a.student = :number) AND e.course.code = :code",
        FormativeExam.class);
    query.setParameter("code", code);
    query.setParameter("number", number);
    return query.getResultList();
  }
}
