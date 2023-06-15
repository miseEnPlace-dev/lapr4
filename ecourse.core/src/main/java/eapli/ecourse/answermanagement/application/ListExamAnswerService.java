package eapli.ecourse.answermanagement.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.ecourse.answermanagement.repositories.AnswerRepository;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.studentmanagement.domain.Student;

public class ListExamAnswerService {
  private final AnswerRepository examAnswerRepository;
  private final EvaluationExamRepository evaluationExamRepository;

  public ListExamAnswerService(AnswerRepository examAnswerRepository,
      EvaluationExamRepository evaluationExamRepository) {
    this.examAnswerRepository = examAnswerRepository;
    this.evaluationExamRepository = evaluationExamRepository;
  }

  public List<AnswerDTO> listStudentGrades(Student student, CourseCode code) {
    Collection<Answer> answers = (Collection<Answer>) examAnswerRepository
        .findAllWithStudentMecanographicNumberAndCourseCode(student.identity(), code);

    List<AnswerDTO> result = (List<AnswerDTO>) convertToDTO(answers);

    Collection<EvaluationExam> evaluationExams = (Collection<EvaluationExam>) evaluationExamRepository
        .findAllCourseExamsWithNoAnswersFromStudent(code, student.identity());

    Collection<AnswerDTO> notTakenExams = createNotTakenExams(student, evaluationExams);
    if (!notTakenExams.isEmpty())
      for (AnswerDTO a : notTakenExams)
        result.add(a);

    return result;
  }

  public Collection<AnswerDTO> listExamGrades(Exam exam, Collection<Student> studentsInCourse) {
    Collection<Answer> answers = (Collection<Answer>) examAnswerRepository.findAllWithExam(exam);

    Collection<AnswerDTO> result = new ArrayList<>((Collection<? extends AnswerDTO>) convertToDTO(answers));

    Set<Student> studentWhoAnswered = new HashSet<>();

    for (Answer examAnswer : answers)
      studentWhoAnswered.add(examAnswer.student());

    for (Student student : studentsInCourse) {
      if (!studentWhoAnswered.contains(student)) {
        result.add(new AnswerDTO(student.identity().toString(), student.user().name().toString(),
            exam.title().toString(), exam.course().title().toString(), null, null));
      }
    }

    return result;
  }

  private Collection<AnswerDTO> createNotTakenExams(Student student, Collection<? extends Exam> exams) {
    Collection<AnswerDTO> examAnswers = new ArrayList<>();

    for (Exam exam : exams) {
      AnswerDTO dto = new AnswerDTO(student.identity().toString(), student.user().name().toString(),
          exam.title().toString(), exam.course().title().toString(), null, null);
      examAnswers.add(dto);
    }

    return examAnswers;
  }

  private Iterable<AnswerDTO> convertToDTO(Iterable<Answer> answers) {
    return StreamSupport.stream(answers.spliterator(), true)
        .map(Answer::toDto)
        .collect(java.util.stream.Collectors.toList());
  }
}
