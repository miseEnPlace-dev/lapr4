package eapli.ecourse.teachermanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class TeacherTest {
  @Test
  public void testTeacherCreation() {
    SystemUser user = getDummyUser();
    TaxPayerNumber taxPayerNumber = TaxPayerNumber.valueOf("123456789");
    Acronym acronym = Acronym.valueOf("ABC");
    BirthDate birthDate = BirthDate.valueOf(Calendar.getInstance());
    Teacher teacher = new Teacher(user, taxPayerNumber, acronym, birthDate);
    assertEquals(user, teacher.user());
    assertEquals(taxPayerNumber, teacher.taxPayerNumber());
    assertEquals(acronym, teacher.acronym());
    assertEquals(birthDate, teacher.birthDate());
  }

  private SystemUser getDummyUser() {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with("username", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(ClientRoles.STUDENT)
        .build();
  }

  private SystemUser getSecondDummyUser() {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with("username2", "duMMy2", "dummY", "dummY", "b@a.ro").withRoles(ClientRoles.STUDENT)
        .build();
  }

  @Test
  public void testTeacherEquality() {
    SystemUser user1 = getDummyUser();
    SystemUser user2 = getSecondDummyUser();
    TaxPayerNumber taxPayerNumber1 = TaxPayerNumber.valueOf("123456789");
    TaxPayerNumber taxPayerNumber2 = TaxPayerNumber.valueOf("987654321");
    Acronym acronym1 = Acronym.valueOf("ABC");
    Acronym acronym2 = Acronym.valueOf("DEF");
    BirthDate birthDate1 = BirthDate.valueOf(Calendar.getInstance());
    BirthDate birthDate2 = BirthDate.valueOf(Calendar.getInstance());
    Teacher teacher1 = new Teacher(user1, taxPayerNumber1, acronym1, birthDate1);
    Teacher teacher2 = new Teacher(user2, taxPayerNumber2, acronym2, birthDate2);
    Teacher teacher3 = new Teacher(user1, taxPayerNumber1, acronym1, birthDate1);
    assertNotEquals(teacher1, teacher2);
    assertEquals(teacher1, teacher3);
  }
}
