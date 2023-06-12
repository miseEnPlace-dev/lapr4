package eapli.ecourse.persistence.impl.inmemory;

import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.ecourse.exammanagement.repositories.FormativeExamRequestRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryFormativeExamRequestRepository extends
    InMemoryDomainRepository<FormativeExamRequest, ExamIdentifier> implements FormativeExamRequestRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<FormativeExamRequest> findAllFormativeRequestByCourse(Optional<Course> course) {
    throw new UnsupportedOperationException("Unimplemented method 'findAllFormativeRequestByCourse'");
  }
}
