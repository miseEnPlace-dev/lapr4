package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.domain.AnswerId;
import eapli.ecourse.answermanagement.repositories.AnswerRepository;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamInfo;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamSection;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionType;
import eapli.ecourse.questionmanagement.domain.ShortAnswerQuestion;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.actions.Action;

public class AnswerBootstrapper implements Action {
  private AnswerRepository answerRepo = PersistenceContext.repositories().answers();
  private QuestionRepository questionRepo = PersistenceContext.repositories().questions();
  private EvaluationExamRepository examRepo = PersistenceContext.repositories().evaluationExams();

  @Override
  public boolean execute() {
    Course course = PersistenceContext.repositories().courses().ofIdentity(CourseCode.valueOf("2222")).get();
    Teacher teacher = PersistenceContext.repositories().teachers().ofIdentity(TaxPayerNumber.valueOf("987654321"))
        .get();
    Student student = PersistenceContext.repositories().students()
        .ofIdentity(MecanographicNumber.valueOf("987654321"))
        .get();
    List<EvaluationExamSection> sections = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    Question q = new ShortAnswerQuestion(QuestionBody.valueOf("question body"), QuestionType.REGULAR);
    questions.add(questionRepo.save(q));

    sections.add(new EvaluationExamSection(SectionIdentifier.valueOf("section 1"),
        SectionTitle.valueOf("section 1 title"), SectionDescription.valueOf("section 1 description"), questions));
    EvaluationExam exam = new EvaluationExam(course, teacher, ExamIdentifier.valueOf("12345"),
        ExamTitle.valueOf("Exam title"), ExamDescription.valueOf("description"), sections,
        Time.valueOf(Calendar.getInstance()), Time.valueOf(Calendar.getInstance()), ExamInfo.ON_SUBMIT,
        ExamInfo.AFTER_CLOSING, ExamScore.valueOf(100.0));

    Exam e = examRepo.save(exam);
    Answer answer = new Answer(AnswerId.newID(), student, e, ExamScore.valueOf(60.0));
    answerRepo.save(answer);
    Answer a = new Answer(AnswerId.newID(), student, e, ExamScore.valueOf(99.0));
    answerRepo.save(a);
    return false;
  }
}
