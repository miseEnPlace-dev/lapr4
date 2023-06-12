package eapli.ecourse.exammanagement.repositories;

import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.framework.domain.repositories.DomainRepository;

public interface FormativeExamRequestRepository extends DomainRepository<ExamIdentifier, FormativeExamRequest> {

}
