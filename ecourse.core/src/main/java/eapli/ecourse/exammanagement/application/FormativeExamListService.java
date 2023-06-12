package eapli.ecourse.exammanagement.application;

import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.repositories.FormativeExamRequestRepository;

import java.util.Optional;

public class FormativeExamListService {
  private FormativeExamRequestRepository repository;

  public FormativeExamListService(FormativeExamRequestRepository formativeExamRepository) {
    this.repository = formativeExamRepository;
  }
}
