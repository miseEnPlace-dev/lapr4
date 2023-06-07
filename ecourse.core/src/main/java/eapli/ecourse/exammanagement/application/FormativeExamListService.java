package eapli.ecourse.exammanagement.application;


import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FormativeExamListService {
  private FormativeExamRepository repository;

  public FormativeExamListService(FormativeExamRepository formativeExamRepository) {
    this.repository = formativeExamRepository;
  }

  public Optional<FormativeExam> findExamByCode(final ExamCode code) {
    return repository.ofIdentity(code);
  }

  public Iterable<FormativeExamDTO> findAllCourseExams(Course course) {
    final Iterable<FormativeExam> exams = repository.findAllCourseExams(course);

    return convertToDto(exams);
  }

  private Iterable<FormativeExamDTO> convertToDto(Iterable<FormativeExam> exams) {
    return StreamSupport.stream(exams.spliterator(), true)
      .map(FormativeExam::toDto)
      .collect(Collectors.toUnmodifiableList());
  }
}
