package eapli.ecourse.exammanagement.repositories;

import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.framework.domain.repositories.DomainRepository;

public interface FormativeExamRequestRepository extends DomainRepository<ExamIdentifier, FormativeExamRequest> {

  Iterable<FormativeExamRequest> findAllFormativeRequestByCourse(Optional<Course> course);
}
