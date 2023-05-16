package eapli.ecourse.exammanagement.domain.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.dto.ExamDTO;
import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExamListService {

  private ExamRepository examRepository;

  public ExamListService(ExamRepository examRepository) {
    this.examRepository = examRepository;
  }

  public Iterable<ExamDTO> listAllCourseExams(Course course) {
    final Iterable<Exam> types = examRepository.findAllCourseExams(course);
    return convertToDto(types);
  }


  private Iterable<ExamDTO> convertToDto(Iterable<Exam> exams) {
    return StreamSupport.stream(exams.spliterator(), true)
      .map(Exam::toDto)
      .collect(Collectors.toUnmodifiableList());
  }
}
