package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaFormativeExamRepository extends JpaAutoTxRepository<FormativeExam, ExamCode, ExamCode>
    implements FormativeExamRepository {
  public JpaFormativeExamRepository(final TransactionalContext autoTx) {
    super(autoTx, "code");
  }

  public JpaFormativeExamRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "code");
  }

  @Override
  public Iterable<FormativeExam> findAllCourseExams(Course course) {
    return match("e.course.code = :course", "course", course.code());
  }
}
