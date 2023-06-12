package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.domain.AnswerId;
import eapli.ecourse.answermanagement.repositories.AnswerRepository;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAnswerRepository extends InMemoryDomainRepository<Answer, AnswerId>
    implements AnswerRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<Answer> findAllWithStudentMecanographicNumberAndCourseCode(MecanographicNumber number,
      CourseCode code) {
    return match(e -> e.student().identity().equals(number) && e.exam().course().code().equals(code));
  }

  public Iterable<Answer> findAllWithExam(Exam exam) {
    return match(e -> e.exam().equals(exam));
  }
}
