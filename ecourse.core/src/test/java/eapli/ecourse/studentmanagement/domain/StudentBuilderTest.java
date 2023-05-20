package eapli.ecourse.studentmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import eapli.ecourse.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class StudentBuilderTest {

  @Test
  public void testStudentBuilder() {
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    SystemUser systemUser = userBuilder.withName("first", "last").withEmail("email@email.com").withUsername("username")
        .withPassword("123Pa1").build();
    MecanographicNumber mecanographicNumber = new MecanographicNumber("12345");
    Student student = new StudentBuilder()
        .withSystemUser(systemUser)
        .withMecanographicNumber(mecanographicNumber)
        .build();
    assertEquals(systemUser, student.user());
    assertEquals(mecanographicNumber, student.mecanographicNumber());
  }

  @Test
  public void testStudentBuilderWithInvalidMecanographicNumber() {
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    SystemUser systemUser = userBuilder.withName("first", "last").withEmail("email@email.com").withUsername("username")
        .withPassword("123Pa1").build();
    String invalidMecanographicNumber = "";
    assertThrows(IllegalArgumentException.class, () -> {
      new StudentBuilder()
          .withSystemUser(systemUser)
          .withMecanographicNumber(invalidMecanographicNumber)
          .build();
    });
  }
}
