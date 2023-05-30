package eapli.ecourse.answermanagement.application;

import java.util.stream.StreamSupport;

import eapli.ecourse.answermanagement.domain.ExamAnswer;
import eapli.ecourse.answermanagement.dto.ExamAnswerDTO;
import eapli.ecourse.answermanagement.repositories.ExamAnswerRepository;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
import eapli.ecourse.studentmanagement.domain.Student;

public class ListExamAnswerService {
  private final ExamAnswerRepository examAnswerRepository;
  private final EvaluationExamRepository evaluationExamRepository;
  private final FormativeExamRepository formativeExamRepository;

  public ListExamAnswerService(ExamAnswerRepository examAnswerRepository,
      EvaluationExamRepository evaluationExamRepository,
      FormativeExamRepository formativeExamRepository) {
    this.examAnswerRepository = examAnswerRepository;
    this.evaluationExamRepository = evaluationExamRepository;
    this.formativeExamRepository = formativeExamRepository;
  }

  public Iterable<ExamAnswerDTO> listStudentGrades(Student student, Course course) {
    Iterable<ExamAnswer> answers = examAnswerRepository.findAllWithStudentMecanographicNumberAndCourseCode(
        student.identity(),
        course.code());

    // TODO
    return null;
  }

  private Iterable<ExamAnswerDTO> convertToDTO(Iterable<ExamAnswer> enrollments) {
    return StreamSupport.stream(enrollments.spliterator(), true)
        .map(ExamAnswer::toDto)
        .collect(java.util.stream.Collectors.toUnmodifiableList());
  }
}
