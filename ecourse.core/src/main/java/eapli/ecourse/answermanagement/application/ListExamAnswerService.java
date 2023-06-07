package eapli.ecourse.answermanagement.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.domain.ExamAnswer;
import eapli.ecourse.answermanagement.dto.ExamAnswerDTO;
import eapli.ecourse.answermanagement.repositories.ExamAnswerRepository;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
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

  public Collection<ExamAnswerDTO> listStudentGrades(Student student, CourseCode code) {
    Collection<ExamAnswer> answers = (Collection<ExamAnswer>) examAnswerRepository
        .findAllWithStudentMecanographicNumberAndCourseCode(
            student.identity(), code);

    Collection<ExamAnswerDTO> result = (Collection<ExamAnswerDTO>) convertToDTO(answers);

    Collection<EvaluationExam> evaluationExams = (Collection<EvaluationExam>) evaluationExamRepository
        .findAllCourseExamsWithNoAnswersFromStudent(code, student.identity());

    result.addAll(createNotTakenExams(student, evaluationExams));

    Collection<FormativeExam> formativeExams = (Collection<FormativeExam>) formativeExamRepository
        .findAllCourseExamsWithNoAnswersFromStudent(code, student.identity());

    result.addAll(createNotTakenExams(student, formativeExams));

    return result;
  }

public Collection<ExamAnswerDTO> listExamGrades(Exam exam, Collection<Student> studentsInCourse) {

  Collection<ExamAnswer> answers = (Collection<ExamAnswer>) examAnswerRepository.findAllWithExam(exam);

  Collection<ExamAnswerDTO> result = (Collection<ExamAnswerDTO>) convertToDTO(answers);

  Set<Student> studentWhoAnswered = new HashSet<>();
  for (ExamAnswer examAnswer: answers) {
    studentWhoAnswered.add(examAnswer.student());
  }

  for (Student student: studentsInCourse) {
    if (!studentWhoAnswered.contains(student))
      result.add(new ExamAnswerDTO(student.identity().toString(), student.user().name().toString(),
        exam.title().toString(), exam.type(), "N/a"));
  }

  return result;
}

  private Collection<ExamAnswerDTO> createNotTakenExams(Student student, Collection<? extends Exam> exams) {
    Collection<ExamAnswerDTO> examAnswers = new ArrayList<>();

    for (Exam exam : exams) {
      ExamAnswerDTO dto = new ExamAnswerDTO(student.identity().toString(), student.user().name().toString(),
          exam.title().toString(), exam.type(), "N/a");
      examAnswers.add(dto);
    }

    return examAnswers;
  }

  private Iterable<ExamAnswerDTO> convertToDTO(Iterable<ExamAnswer> answers) {
    return StreamSupport.stream(answers.spliterator(), true)
        .map(ExamAnswer::toDto)
        .collect(java.util.stream.Collectors.toUnmodifiableList());
  }
}
