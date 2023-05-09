package eapli.ecourse.studentmanagement.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

/**
 * @author Nuno Bettencourt [NMB] on 03/04/16.
 */
class StudentTest {

  private final String aMecanographicNumber = "abc";
  private final String anotherMecanographicNumber = "xyz";

  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  private SystemUser getNewDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  @Test
  void ensureStudentEqualsPassesForTheSameMecanographicNumber() throws Exception {

    final Student aStudent = new StudentBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    final Student anotherStudent = new StudentBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    final boolean expected = aStudent.equals(anotherStudent);

    assertTrue(expected);
  }

  @Test
  void ensureStudentEqualsFailsForDifferenteMecanographicNumber() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.MANAGER);

    final Student aStudent = new StudentBuilder()
        .withMecanographicNumber(aMecanographicNumber).withSystemUser(getNewDummyUser()).build();

    final Student anotherStudent = new StudentBuilder().withMecanographicNumber(anotherMecanographicNumber)
        .withSystemUser(getNewDummyUser()).build();

    final boolean expected = aStudent.equals(anotherStudent);

    assertFalse(expected);
  }

  @Test
  void ensureStudentEqualsAreTheSameForTheSameInstance() throws Exception {
    final Student aStudent = new Student();

    assertEquals(aStudent, aStudent);
  }

  @Test
  void ensureStudentEqualsFailsForDifferenteObjectTypes() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.MANAGER);

    final Student aStudent = new StudentBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    @SuppressWarnings("unlikely-arg-type")
    final boolean expected = aStudent.equals(getNewDummyUser());

    assertFalse(expected);
  }

  @Test
  void ensureStudentIsTheSameAsItsInstance() throws Exception {
    final Student aStudent = new StudentBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    assertTrue(aStudent.sameAs(aStudent));
  }

  @Test
  void enslientserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.MANAGER);
    final Student aStudent = new StudentBuilder()
        .withMecanographicNumber(aMecanographicNumber).withSystemUser(getNewDummyUser()).build();

    final Student anotherStudent = new StudentBuilder().withMecanographicNumber(anotherMecanographicNumber)
        .withSystemUser(getNewDummyUser()).build();

    assertFalse(aStudent.sameAs(anotherStudent));
  }
}
