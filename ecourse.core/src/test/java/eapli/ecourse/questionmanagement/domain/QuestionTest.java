package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
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

public class QuestionTest {
  private QuestionCode code;
  private QuestionBody body;
  private QuestionType type;
  private Feedback feedback;
  private Course course;

  public Course getNewDummyCourse() {
    return dummyCourse(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"), CourseDescription.valueOf("dummy"),
        EnrolmentLimits.valueOf(10, 20), new CourseState(CourseState.State.CLOSED),
        new CourseEnrolmentState(CourseEnrolmentState.EnrolmentState.CLOSED), getNewDummyTeacher());
  }

  public Teacher getNewDummyTeacher() {
    return new Teacher(getNewDummyUser(), TaxPayerNumber.valueOf("123456789"), Acronym.valueOf("abc"),
        BirthDate.valueOf(Calendar.getInstance()));
  }

  public static Course dummyCourse(final CourseCode code, final CourseTitle title, final CourseDescription description,
      final EnrolmentLimits enrolmentLimits, final CourseState courseState, final CourseEnrolmentState enrolmentState,
      final Teacher teacher) {
    return new Course(code, title, description, enrolmentLimits, courseState, enrolmentState, teacher);
  }

  public SystemUser getNewDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  @Before
  public void setup() {
    code = QuestionCode.newID();
    body = QuestionBody.valueOf("Question Body");
    type = QuestionType.FORMATIVE;
    feedback = Feedback.valueOf("feedback");
    course = getNewDummyCourse();
  }

  @Test
  public void testQuestionCreationWithCourseAndFeedback() {
    Question question = new Question(body, type, feedback, course) {
      @Override
      public boolean sameAs(Object other) {
        return false;
      }
    };
    assertEquals(body, question.body());
    assertEquals(type, question.type());
    assertEquals(feedback, question.feedback());
    assertEquals(course, question.course());
  }

  @Test
  public void testQuestionCreationWithType() {
    Question question = new Question(type) {
      @Override
      public boolean sameAs(Object other) {
        return false;
      }
    };
    assertEquals(type, question.type());
  }

  @Test
  public void testQuestionCreationWithBodyAndType() {
    Question question = new Question(body, type) {
      @Override
      public boolean sameAs(Object other) {
        return false;
      }
    };
    assertEquals(body, question.body());
    assertEquals(type, question.type());
  }

  @Test
  public void testQuestionChangeBody() {
    Question question = new Question(body, type) {
      @Override
      public boolean sameAs(Object other) {
        return false;
      }
    };
    QuestionBody newBody = QuestionBody.valueOf("New Question Body");
    question.changeBody(newBody);
    assertEquals(newBody, question.body());
  }

  @Test
  public void testQuestionChangeFeedback() {
    Question question = new Question(body, type, feedback, course) {
      @Override
      public boolean sameAs(Object other) {
        return false;
      }
    };
    Feedback newFeedback = Feedback.valueOf("new feedback");
    question.changeFeedback(newFeedback);
    assertEquals(newFeedback, question.feedback());
  }
}
