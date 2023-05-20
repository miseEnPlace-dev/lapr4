package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PermissionTypeTest {

  @Test
  public void ensureItIsNotPossibleToCreateWithNullType() {
    assertThrows(IllegalArgumentException.class, () -> new PermissionType(null));
  }

  @Test
  public void ensureCreatePermissionTypeWorks() {
    PermissionType p1 = new PermissionType(PermissionType.Type.READ);
    PermissionType p2 = new PermissionType(PermissionType.Type.WRITE);

    assertTrue(p1.isRead());
    assertTrue(p1.isWrite() == false);

    assertTrue(p2.isWrite());
    assertTrue(p2.isRead() == false);
  }

}
