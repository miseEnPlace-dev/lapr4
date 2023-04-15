package eapli.ecourse.clientusermanagement.domain;

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
    final SystemUserBuilder userBuilder =
        new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  private SystemUser getNewDummyUser() {
    return dummyUser("dummy", ClientRoles.ADMIN);
  }

  @Test
  void ensureClientUserEqualsPassesForTheSameMecanographicNumber() throws Exception {

    final ClientUser aClientUser = new ClientUserBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    final ClientUser anotherClientUser = new ClientUserBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    final boolean expected = aClientUser.equals(anotherClientUser);

    assertTrue(expected);
  }

  @Test
  void ensureClientUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.ADMIN);

    final ClientUser aClientUser = new ClientUserBuilder()
        .withMecanographicNumber(aMecanographicNumber).withSystemUser(getNewDummyUser()).build();

    final ClientUser anotherClientUser =
        new ClientUserBuilder().withMecanographicNumber(anotherMecanographicNumber)
            .withSystemUser(getNewDummyUser()).build();

    final boolean expected = aClientUser.equals(anotherClientUser);

    assertFalse(expected);
  }

  @Test
  void ensureClientUserEqualsAreTheSameForTheSameInstance() throws Exception {
    final ClientUser aClientUser = new ClientUser();

    assertEquals(aClientUser, aClientUser);
  }

  @Test
  void ensureClientUserEqualsFailsForDifferenteObjectTypes() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.ADMIN);

    final ClientUser aClientUser = new ClientUserBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    @SuppressWarnings("unlikely-arg-type")
    final boolean expected = aClientUser.equals(getNewDummyUser());

    assertFalse(expected);
  }

  @Test
  void ensureClientUserIsTheSameAsItsInstance() throws Exception {
    final ClientUser aClientUser = new ClientUserBuilder().withMecanographicNumber("DUMMY")
        .withSystemUser(getNewDummyUser()).build();

    assertTrue(aClientUser.sameAs(aClientUser));
  }

  @Test
  void enslientserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.ADMIN);
    final ClientUser aClientUser = new ClientUserBuilder()
        .withMecanographicNumber(aMecanographicNumber).withSystemUser(getNewDummyUser()).build();

    final ClientUser anotherClientUser =
        new ClientUserBuilder().withMecanographicNumber(anotherMecanographicNumber)
            .withSystemUser(getNewDummyUser()).build();

    assertFalse(aClientUser.sameAs(anotherClientUser));
  }
}