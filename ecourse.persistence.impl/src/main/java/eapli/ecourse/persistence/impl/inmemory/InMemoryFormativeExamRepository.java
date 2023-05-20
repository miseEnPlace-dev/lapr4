package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryFormativeExamRepository extends InMemoryDomainRepository<FormativeExam, ExamCode>
    implements FormativeExamRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<FormativeExam> findAllCourseExams(Course course) {
    return match(e -> e.course().equals(course));
  }
}
