package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.answermanagement.domain.ExamAnswer;
import eapli.ecourse.answermanagement.domain.ExamAnswerId;
import eapli.ecourse.answermanagement.repositories.ExamAnswerRepository;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryExamAnswerRepository extends InMemoryDomainRepository<ExamAnswer, ExamAnswerId>
    implements ExamAnswerRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<ExamAnswer> findAllWithStudentMecanographicNumberAndCourseCode(MecanographicNumber number,
      CourseCode code) {
    return match(e -> e.student().identity().equals(number) && e.exam().course().code().equals(code));
  }
}
