package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserPermissionIDTest {

  @Test
  public void ensureUserPermissionIDHasID() {
    UserPermissionID userPermissionID = UserPermissionID.newID();
    assert (userPermissionID.toString() != null);
  }

  @Test
  public void ensureToStringWorks() {
    UserPermissionID userPermissionID = UserPermissionID.valueOf("UserPermissionID");
    assertEquals("UserPermissionID", userPermissionID.toString());
  }

  @Test
  public void ensureNewIDWorks() {
    UserPermissionID userPermissionID = UserPermissionID.newID();
    assert (userPermissionID.toString() != null);
  }

  @Test
  public void ensureCompareToWorks() {
    UserPermissionID userPermissionID1 = UserPermissionID.valueOf("UserPermissionID1");
    UserPermissionID userPermissionID2 = UserPermissionID.valueOf("UserPermissionID2");
    assert (userPermissionID1.compareTo(userPermissionID2) != 0);
  }

}
