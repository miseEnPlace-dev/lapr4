package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.Month;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class UserPermissionTest {
  private SystemUser user;

  @Before
  public void setUp() {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    this.user = userBuilder.with("username", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(ClientRoles.STUDENT)
        .build();
  }

  @Test
  public void ensureUserPermissionHasId() {
    assertThrows(IllegalArgumentException.class,
        () -> new UserPermission(null, Calendar.getInstance(), Calendar.getInstance(),
            new PermissionType(), user));
  }

  @Test
  public void ensureUserPermissionHasCreatedAt() {
    assertThrows(IllegalArgumentException.class,
        () -> new UserPermission(new UserPermissionID(), null, Calendar.getInstance(),
            new PermissionType(), user));
  }

  @Test
  public void ensureUserPermissionHasPermissionType() {
    assertThrows(IllegalArgumentException.class,
        () -> new UserPermission(new UserPermissionID(), Calendar.getInstance(), Calendar.getInstance(),
            null, user));
  }

  @Test
  public void ensureUserPermissionHasUser() {
    assertThrows(IllegalArgumentException.class,
        () -> new UserPermission(new UserPermissionID(), Calendar.getInstance(), Calendar.getInstance(),
            new PermissionType(), null));
  }

  @Test
  public void ensureIsNotPossibleToCreateWithFutureCreatedAtDate() {
    Calendar futureDate = Calendar.getInstance();
    futureDate.add(Calendar.YEAR, 1);

    assertThrows(IllegalArgumentException.class,
        () -> new UserPermission(new UserPermissionID(), futureDate, Calendar.getInstance(),
            new PermissionType(), user));
  }

  @Test
  public void ensureIsNotPossibleToCreateWithCreatedAtAfterUpdatedAt() {
    Calendar createdAt = Calendar.getInstance();
    createdAt.set(2020, Month.JANUARY.getValue(), 1);

    Calendar updatedAt = Calendar.getInstance();
    updatedAt.set(2019, Month.JANUARY.getValue(), 1);

    assertThrows(IllegalArgumentException.class,
        () -> new UserPermission(new UserPermissionID(), createdAt, updatedAt,
            new PermissionType(), user));
  }

  @Test
  public void ensureSameAsWorks() {
    Calendar differentDate = Calendar.getInstance();
    differentDate.add(Calendar.YEAR, -1);

    UserPermission up1 = new UserPermission(new UserPermissionID(), Calendar.getInstance(), Calendar.getInstance(),
        new PermissionType(), user);
    UserPermission up2 = new UserPermission(new UserPermissionID(), differentDate, Calendar.getInstance(),
        new PermissionType(), user);

    assertTrue(up1.sameAs(up1));
    assertFalse(up1.sameAs(up2));
  }

  @Test
  public void testCanWrite() {
    UserPermission up = new UserPermission(new UserPermissionID(), Calendar.getInstance(), Calendar.getInstance(),
        new PermissionType(), user);

    assertTrue(up.canWrite(user.username()));
  }
}
