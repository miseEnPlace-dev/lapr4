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
class ClientUserTest {

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
  void ensureClientUserEqualsPassesForTheSameMecanographicNumber() throws Exception {

    final Student aClientUser = new StudentBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    final Student anotherClientUser = new StudentBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    final boolean expected = aClientUser.equals(anotherClientUser);

    assertTrue(expected);
  }

  @Test
  void ensureClientUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.MANAGER);

    final Student aClientUser = new StudentBuilder()
        .withMecanographicNumber(aMecanographicNumber).withSystemUser(getNewDummyUser()).build();

    final Student anotherClientUser = new StudentBuilder().withMecanographicNumber(anotherMecanographicNumber)
        .withSystemUser(getNewDummyUser()).build();

    final boolean expected = aClientUser.equals(anotherClientUser);

    assertFalse(expected);
  }

  @Test
  void ensureClientUserEqualsAreTheSameForTheSameInstance() throws Exception {
    final Student aClientUser = new Student();

    assertEquals(aClientUser, aClientUser);
  }

  @Test
  void ensureClientUserEqualsFailsForDifferenteObjectTypes() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.MANAGER);

    final Student aClientUser = new StudentBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    @SuppressWarnings("unlikely-arg-type")
    final boolean expected = aClientUser.equals(getNewDummyUser());

    assertFalse(expected);
  }

  @Test
  void ensureClientUserIsTheSameAsItsInstance() throws Exception {
    final Student aClientUser = new StudentBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    assertTrue(aClientUser.sameAs(aClientUser));
  }

  @Test
  void enslientserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.MANAGER);
    final Student aClientUser = new StudentBuilder()
        .withMecanographicNumber(aMecanographicNumber).withSystemUser(getNewDummyUser()).build();

    final Student anotherClientUser = new StudentBuilder().withMecanographicNumber(anotherMecanographicNumber)
        .withSystemUser(getNewDummyUser()).build();

    assertFalse(aClientUser.sameAs(anotherClientUser));
  }
}
