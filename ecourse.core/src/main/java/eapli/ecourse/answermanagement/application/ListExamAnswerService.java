package eapli.ecourse.answermanagement.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.ecourse.answermanagement.repositories.ExamAnswerRepository;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.studentmanagement.domain.Student;

public class ListExamAnswerService {
  private final ExamAnswerRepository examAnswerRepository;
  private final EvaluationExamRepository evaluationExamRepository;

  public ListExamAnswerService(ExamAnswerRepository examAnswerRepository,
      EvaluationExamRepository evaluationExamRepository) {
    this.examAnswerRepository = examAnswerRepository;
    this.evaluationExamRepository = evaluationExamRepository;
  }

  public Collection<AnswerDTO> listStudentGrades(Student student, CourseCode code) {
    Collection<Answer> answers = (Collection<Answer>) examAnswerRepository
        .findAllWithStudentMecanographicNumberAndCourseCode(
            student.identity(), code);

    Collection<AnswerDTO> result = (Collection<AnswerDTO>) convertToDTO(answers);

    Collection<EvaluationExam> evaluationExams = (Collection<EvaluationExam>) evaluationExamRepository
        .findAllCourseExamsWithNoAnswersFromStudent(code, student.identity());

    result.addAll(createNotTakenExams(student, evaluationExams));

    return result;
  }

  public Collection<AnswerDTO> listExamGrades(Exam exam, Collection<Student> studentsInCourse) {

    Collection<Answer> answers = (Collection<Answer>) examAnswerRepository.findAllWithExam(exam);

    Collection<AnswerDTO> result = (Collection<AnswerDTO>) convertToDTO(answers);

    Set<Student> studentWhoAnswered = new HashSet<>();
    for (Answer examAnswer : answers) {
      studentWhoAnswered.add(examAnswer.student());
    }

    for (Student student : studentsInCourse) {
      if (!studentWhoAnswered.contains(student))
        result.add(new AnswerDTO(student.identity().toString(), student.user().name().toString(),
            exam.title().toString(), "N/a", null));
    }

    return result;
  }

  private Collection<AnswerDTO> createNotTakenExams(Student student, Collection<? extends Exam> exams) {
    Collection<AnswerDTO> examAnswers = new ArrayList<>();

    for (Exam exam : exams) {
      AnswerDTO dto = new AnswerDTO(student.identity().toString(), student.user().name().toString(),
          exam.title().toString(), "N/a", null);
      examAnswers.add(dto);
    }

    return examAnswers;
  }

  private Iterable<AnswerDTO> convertToDTO(Iterable<Answer> answers) {
    return StreamSupport.stream(answers.spliterator(), true)
        .map(Answer::toDto)
        .collect(java.util.stream.Collectors.toUnmodifiableList());
  }
}
