package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.exammanagement.application.GenerateStructureFormativeExamService;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
import eapli.ecourse.exammanagement.domain.parsers.ANTLR4ExamParser;
import eapli.ecourse.questionmanagement.domain.Feedback;
import eapli.ecourse.questionmanagement.domain.MatchingQuestion;
import eapli.ecourse.questionmanagement.domain.MissingWordsQuestion;
import eapli.ecourse.questionmanagement.domain.MultipleChoiceQuestion;
import eapli.ecourse.questionmanagement.domain.NumericalQuestion;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.ecourse.questionmanagement.domain.QuestionType;
import eapli.ecourse.questionmanagement.domain.ShortAnswerQuestion;
import eapli.ecourse.questionmanagement.domain.TrueFalseQuestion;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class GenerateStructureFormativeExamServiceTest {

  public Course getDummyCourse() {
    return new Course(CourseCode.valueOf("id"), CourseTitle.valueOf("Test Course"),
        CourseDescription.valueOf("Test Description"), EnrolmentLimits.valueOf(10, 20),
        new CourseState(CourseState.State.CLOSED), new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED),
        null);
  }

  private Teacher getDummyTeacher() {
    return new Teacher(getDummyUser(), TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("dummy"),
        BirthDate.valueOf(Calendar.getInstance()));
  }

  private SystemUser getDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  private FormativeExam exam;

  // MultipleChoiceQuestion

  private void addCorrectAnswerMultipleChoiceQuestion(MultipleChoiceQuestion question) {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    Double weight = 1.0;
    question.addCorrectAnswer(identifier, weight);
    Map<QuestionIdentifier, Double> expected = new HashMap<>();
    expected.put(identifier, weight);
  }

  private void addOptionMultipleChoiceQuestion(MultipleChoiceQuestion question) {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    String option = "Paris";
    question.addOption(identifier, option);
    Map<QuestionIdentifier, String> expected = new HashMap<>();
    expected.put(identifier, option);
  }

  // ShortAnswerQuestion

  private void addCorrectAnswerShortAnswer(ShortAnswerQuestion question) {
    String correctAnswer = "Lisbon";
    Double grade = 1.0;
    question.addCorrectAnswer(correctAnswer, grade);
    Map<String, Double> expected = new HashMap<>();
    expected.put(correctAnswer, grade);
  }

  // MissingWordsQuestion

  private void addMissingWord(MissingWordsQuestion question) {
    String missingWord = "Lisbon";
    question.addMissingWord(missingWord);
  }

  private void addOptionMissingWord(MissingWordsQuestion question) {
    String option = "Euro";
    question.addOption(option);
  }

  // MatchingQuestion

  private void addCorrectMatch(MatchingQuestion question) {
    String option = "1";
    String match = "1";
    question.addCorrectMatch(option, match);
  }

  private void addMatch(MatchingQuestion question) {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    String match = "France";
    question.addMatch(identifier, match);
  }

  private void addMatchOption(MatchingQuestion question) {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    String option = "France";
    question.addOption(identifier, option);
  }

  private void addFeedbackMatch(MatchingQuestion question) {
    QuestionIdentifier identifier = QuestionIdentifier.valueOf("1");
    Feedback feedback = Feedback.valueOf("This is feedback");
    question.addFeedback(identifier, feedback);
  }

  @Test
  public void ensureIsPossibleToCreateFormativeExamString() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, ExamScore.valueOf(100d),
        sections);
    QuestionBody body = QuestionBody.valueOf("This is a test question");
    QuestionType type = QuestionType.FORMATIVE;

    TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion(body, type, false);
    NumericalQuestion numericalQuestion = new NumericalQuestion(body, type, 1, 0.5);
    SectionTitle sectionTitle = SectionTitle.valueOf("Section title");
    SectionIdentifier id = SectionIdentifier.valueOf("id");
    SectionDescription des = SectionDescription.valueOf("description");

    Collection<Question> questions = new ArrayList<>();
    questions.add(trueFalseQuestion);
    questions.add(numericalQuestion);

    FormativeExamSection section = new FormativeExamSection(id, sectionTitle, des, questions);
    sections.add(section);

    GenerateStructureFormativeExamService service = new GenerateStructureFormativeExamService(exam);
    String struct = service.generateStructureString();
    System.out.println(struct);
  }

  @Test
  public void ensureFormativeExamStringIsCorrect() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, ExamScore.valueOf(100d),
        sections);
    QuestionBody body = QuestionBody.valueOf("This is a test question");
    QuestionType type = QuestionType.FORMATIVE;

    TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion(body, type, false);
    NumericalQuestion numericalQuestion = new NumericalQuestion(body, type, 1, 0.5);
    SectionTitle sectionTitle = SectionTitle.valueOf("Section title");
    SectionIdentifier id = SectionIdentifier.valueOf("id");
    SectionDescription des = SectionDescription.valueOf("description");

    Collection<Question> questions = new ArrayList<>();
    questions.add(trueFalseQuestion);
    questions.add(numericalQuestion);

    FormativeExamSection section = new FormativeExamSection(id, sectionTitle, des, questions);
    sections.add(section);

    String expected = "@start-exam 1;@title \"Test Exam\";@description \"This is a test exam\";@feedback on-submit;@grade on-submit;@start-section id;@title \"Section title\";@description \"description\";@start-question@type true-false;@score 50.0;@question-body \"This is a test question\";@correct-answer false;@end-question;@start-question@type numerical;@score 50.0;@question-body \"This is a test question\";@correct-answer1.0;@accepted-error0.5;@end-question;@end-section;@end-exam;";

    GenerateStructureFormativeExamService service = new GenerateStructureFormativeExamService(exam);
    String struct = service.generateStructureString();

    assertEquals(expected, struct);
  }

  @Test
  public void ensureFormativeExamStringIsCorrectWithMultipleSections() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, ExamScore.valueOf(100d),
        sections);
    QuestionBody body = QuestionBody.valueOf("This is a test question");
    QuestionType type = QuestionType.FORMATIVE;

    TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion(body, type, false);
    NumericalQuestion numericalQuestion = new NumericalQuestion(body, type, 1, 0.5);
    SectionTitle sectionTitle = SectionTitle.valueOf("Section title");
    SectionIdentifier id = SectionIdentifier.valueOf("id");
    SectionDescription des = SectionDescription.valueOf("description");

    Collection<Question> questions = new ArrayList<>();
    questions.add(trueFalseQuestion);
    questions.add(numericalQuestion);

    FormativeExamSection section = new FormativeExamSection(id, sectionTitle, des, questions);
    sections.add(section);
    sections.add(section);

    String expected = "@start-exam 1;@title \"Test Exam\";@description \"This is a test exam\";@feedback on-submit;@grade on-submit;@start-section id;@title \"Section title\";@description \"description\";@start-question@type true-false;@score 25.0;@question-body \"This is a test question\";@correct-answer false;@end-question;@start-question@type numerical;@score 25.0;@question-body \"This is a test question\";@correct-answer1.0;@accepted-error0.5;@end-question;@end-section;@start-section id;@title \"Section title\";@description \"description\";@start-question@type true-false;@score 25.0;@question-body \"This is a test question\";@correct-answer false;@end-question;@start-question@type numerical;@score 25.0;@question-body \"This is a test question\";@correct-answer1.0;@accepted-error0.5;@end-question;@end-section;@end-exam;";

    GenerateStructureFormativeExamService service = new GenerateStructureFormativeExamService(exam);
    String struct = service.generateStructureString();

    assertEquals(expected, struct);
  }

  @Test
  public void ensureFormativeExamStringIsCorrectWithMultipleQuestions() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, ExamScore.valueOf(100d),
        sections);
    QuestionBody body = QuestionBody.valueOf("This is a test question");
    QuestionType type = QuestionType.FORMATIVE;

    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(body, type);
    addCorrectAnswerMultipleChoiceQuestion(multipleChoiceQuestion);
    addOptionMultipleChoiceQuestion(multipleChoiceQuestion);

    SectionTitle sectionTitle = SectionTitle.valueOf("Section title");
    SectionIdentifier id = SectionIdentifier.valueOf("id");
    SectionDescription des = SectionDescription.valueOf("description");

    Collection<Question> questions = new ArrayList<>();
    questions.add(multipleChoiceQuestion);

    FormativeExamSection section = new FormativeExamSection(id, sectionTitle, des, questions);

    sections.add(section);

    String examStruct = "@start-exam 1;@title \"Test Exam\";@description \"This is a test exam\";@feedback on-submit;@grade on-submit;@start-section id;@title \"Section title\";@description \"description\";@start-question@type multiple-choice;@score 100.0;@question-body \"This is a test question\";@start-correct-answers@correct-answer 1 1.0;@end-correct-answers;@start-options@option 1 \"Paris\";@end-options;@end-question;@end-section;@end-exam;";

    GenerateStructureFormativeExamService service = new GenerateStructureFormativeExamService(exam);

    String expected = service.generateStructureString();

    assertEquals(examStruct, expected);
  }

  @Test
  public void ensureTest() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("exam");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, ExamScore.valueOf(100d),
        sections);
    QuestionBody body = QuestionBody.valueOf("This is a test question");
    QuestionType type = QuestionType.FORMATIVE;

    TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion(body, type, false);

    NumericalQuestion numericalQuestion = new NumericalQuestion(body, type, 1, 0.5);

    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(body, type);
    addCorrectAnswerMultipleChoiceQuestion(multipleChoiceQuestion);
    addOptionMultipleChoiceQuestion(multipleChoiceQuestion);

    ShortAnswerQuestion shortAnswerQuestion = new ShortAnswerQuestion(
        new QuestionBody("What is the capital of Portugal?"), QuestionType.FORMATIVE);
    addCorrectAnswerShortAnswer(shortAnswerQuestion);

    MissingWordsQuestion missingWordsQuestion = new MissingWordsQuestion(
        new QuestionBody("The capital of Portugal is ___. The currency used in Portugal is the ___."),
        QuestionType.FORMATIVE);
    addMissingWord(missingWordsQuestion);
    addOptionMissingWord(missingWordsQuestion);

    MatchingQuestion matchingQuestion = new MatchingQuestion(
        new QuestionBody("Match the following countries with their capitals"),
        QuestionType.FORMATIVE);
    addMatch(matchingQuestion);
    addMatchOption(matchingQuestion);
    addCorrectMatch(matchingQuestion);
    addFeedbackMatch(matchingQuestion);

    SectionTitle sectionTitle = SectionTitle.valueOf("Section title");
    SectionIdentifier id = SectionIdentifier.valueOf("id");
    SectionDescription des = SectionDescription.valueOf("description");

    Collection<Question> questions = new ArrayList<>();
    questions.add(trueFalseQuestion);
    questions.add(numericalQuestion);
    questions.add(multipleChoiceQuestion);
    questions.add(shortAnswerQuestion);
    questions.add(missingWordsQuestion);
    questions.add(matchingQuestion);

    FormativeExamSection section = new FormativeExamSection(id, sectionTitle, des, questions);
    sections.add(section);

    GenerateStructureFormativeExamService service = new GenerateStructureFormativeExamService(exam);
    String struct = service.generateStructureString();

    ANTLR4ExamParser parser = new ANTLR4ExamParser();

    System.out.println(struct);

    parser.parseFromString(struct);
  }
}
