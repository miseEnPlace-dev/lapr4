package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.ArrayList;
import java.util.List;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.domain.AnswerId;
import eapli.ecourse.answermanagement.repositories.ExamAnswerRepository;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
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
  private ExamAnswerRepository answerRepo = PersistenceContext.repositories().answers();
  private QuestionRepository questionRepo = PersistenceContext.repositories().questions();
  private FormativeExamRepository examRepo = PersistenceContext.repositories().formativeExams();

  @Override
  public boolean execute() {
    Course course = PersistenceContext.repositories().courses().ofIdentity(CourseCode.valueOf("4444")).get();
    Teacher teacher = PersistenceContext.repositories().teachers().ofIdentity(TaxPayerNumber.valueOf("987654321"))
        .get();
    Student student = PersistenceContext.repositories().students()
        .ofIdentity(MecanographicNumber.valueOf("123456789"))
        .get();
    List<FormativeExamSection> sections = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    Question q = new ShortAnswerQuestion(QuestionBody.valueOf("question body"), QuestionType.FORMATIVE);
    questions.add(questionRepo.save(q));

    sections.add(new FormativeExamSection(SectionIdentifier.valueOf("section 1"),
        SectionTitle.valueOf("section 1 title"), SectionDescription.valueOf("section 1 description"), questions));
    FormativeExam exam = new FormativeExam(course, teacher, ExamIdentifier.valueOf("12345"),
        ExamTitle.valueOf("Exam title"),
        ExamDescription.valueOf("description"), ExamScore.valueOf(.2), sections);

    Exam e = examRepo.save(exam);
    Answer answer = new Answer(AnswerId.newID(), student, e, ExamScore.valueOf(.2));
    answerRepo.save(answer);
    Answer a = new Answer(AnswerId.newID(), student, e, ExamScore.valueOf(1.0));
    answerRepo.save(a);
    return false;
  }
}
