package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.exammanagement.application.GenerateStructureFormativeExamService;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
import eapli.ecourse.questionmanagement.domain.NumericalQuestion;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionType;
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

  @Test
  public void ensureIsPossibleToCreateFormativeExamString() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, null, sections);
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
    String struct = service.generateStructureFile();
    System.out.println(struct);
  }

  @Test
  public void ensureFormativeExamStringIsCorrect() {
    Course course = getDummyCourse();
    ExamIdentifier identifier = ExamIdentifier.valueOf("1");
    ExamTitle title = ExamTitle.valueOf("Test Exam");
    ExamDescription description = ExamDescription.valueOf("This is a test exam");
    Collection<FormativeExamSection> sections = new ArrayList<>();
    exam = new FormativeExam(course, getDummyTeacher(), identifier, title, description, null, sections);
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

    String examStruct = "@start-exam 1;@description \"This is a test exam\";@start-section id;@title \"Section title\";@description \"description\";@start-question@question-body \"This is a test question\";@correct-answer false;@end-question;@start-question@question-body \"This is a test question\";@correct-answer1.0;@accepted-error0.5;@end-question;@end-section;@end-exam;";

    GenerateStructureFormativeExamService service = new GenerateStructureFormativeExamService(exam);
    String struct = service.generateStructureFile();

    assertEquals(examStruct, struct);
  }
}
