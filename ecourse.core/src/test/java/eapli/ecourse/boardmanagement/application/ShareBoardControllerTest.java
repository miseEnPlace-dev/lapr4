package eapli.ecourse.boardmanagement.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.junit.Before;
import org.junit.Test;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.boardmanagement.domain.UserPermission;
import eapli.ecourse.boardmanagement.dto.UserPermissionDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ShareBoardControllerTest extends BoardControllerBaseTest {
  private ShareBoardController ctrl;
  private BoardRepository boardRepository;
  private UserManagementService usrSvc;

  @Before
  public void setup() {
    this.usrSvc = mock(UserManagementService.class);
    this.boardRepository = mock(BoardRepository.class);
    this.ctrl = new ShareBoardController(boardRepository, usrSvc);
  }

  /**
   * Test if board exists.
   */
  @Test
  public void testBoardExists() {
    Board board = getDummyBoard();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    boolean result = ctrl.boardExists(board.identity());
    assertTrue(result);
  }

  /**
   * Test if board does not exist.
   */
  @Test
  public void testBoardDoesNotExist() {
    Board board = getDummyBoard();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.empty());

    boolean result = ctrl.boardExists(board.identity());
    assertFalse(result);
  }

  /**
   * Test if user participates in the board.
   */
  @Test
  public void testIsBoardParticipant() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    UserPermission userPermission = new UserPermission(Calendar.getInstance(),
        Calendar.getInstance(), PermissionType.read(), user);

    board.permissions().add(userPermission);

    boolean result = ctrl.isBoardParticipant(board.identity(), user.username());
    assertTrue(result);
  }

  /**
   * Test if user does not participate in the board.
   */
  @Test
  public void testIsNotBoardParticipant() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser2();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    boolean result = ctrl.isBoardParticipant(board.identity(), user.username());

    assertFalse(result);
  }

  /**
   * Test if user is the board owner.
   */
  @Test
  public void testIsBoardOwner() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    UserPermission userPermission = new UserPermission(Calendar.getInstance(),
        Calendar.getInstance(), PermissionType.write(), user);

    board.permissions().add(userPermission);

    boolean result = ctrl.isBoardOwner(board.identity(), user.username());
    assertTrue(result);
  }

  /**
   * Test if user is not board owner.
   */
  @Test
  public void testIsNotBoardOwner() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser2();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    boolean result = ctrl.isBoardOwner(board.identity(), user.username());
    assertFalse(result);
  }

  /**
   * Test if board is archived.
   */
  @Test
  public void testIsBoardIsArchived() {
    Board board = getDummyArchivedBoard();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    boolean result = ctrl.isBoardArchived(board.identity());
    assertTrue(result);
  }

  /**
   * Test if board is not archived.
   */
  @Test
  public void testIsBoardIsNotArchived() {
    Board board = getDummyBoard();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    boolean result = ctrl.isBoardArchived(board.identity());
    assertFalse(result);
  }

  /**
   * Test if user has permission.
   */
  @Test
  public void testUserHasPermission() {
    Board board = getDummyBoard2();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    UserPermission userPermission = new UserPermission(Calendar.getInstance(),
        Calendar.getInstance(), PermissionType.write(), user);

    board.permissions().add(userPermission);

    UserPermissionDTO result = ctrl.getUserPermission(board.identity(), user.username());

    assertNotNull(result);
    assertTrue(result.getPermissionType().isWrite());
  }

  /**
   * Test if user does not have permission.
   */
  @Test
  public void testUserDoesNotHavePermission() {
    Board board = getDummyBoard2();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    UserPermissionDTO result = ctrl.getUserPermission(board.identity(), user.username());

    assertNull(result);
  }

  /**
   * Test adding a permission to a board.
   */
  @Test
  public void testAddPermission() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));
    when(usrSvc.userOfIdentity(user.username())).thenReturn(Optional.of(user));

    ctrl.addPermission(board.identity(), user.username(), PermissionType.read());

    boolean found = StreamSupport.stream(board.permissions().spliterator(), false).anyMatch(p -> {
      if (p.user().username().equals(user.username())) {
        assertTrue(p.permissionType().isRead());
        return true;
      }
      return false;
    });

    assertTrue(found);
  }

  /**
   * Test updating a permission.
   */
  @Test
  public void testUpdatePermission() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));
    when(usrSvc.userOfIdentity(user.username())).thenReturn(Optional.of(user));

    UserPermission userPermission = new UserPermission(Calendar.getInstance(),
        Calendar.getInstance(), PermissionType.read(), user);

    board.permissions().add(userPermission);

    ctrl.updatePermission(board.identity(), user.username(), PermissionType.write());

    boolean found = StreamSupport.stream(board.permissions().spliterator(), false).anyMatch(p -> {
      if (p.user().username().equals(user.username())) {
        assertTrue(p.permissionType().isWrite());
        return true;
      }
      return false;
    });

    assertTrue(found);
  }

  /**
   * Test removing a permission.
   */
  @Test
  public void testRemovePermission() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));
    when(usrSvc.userOfIdentity(user.username())).thenReturn(Optional.of(user));

    UserPermission userPermission = new UserPermission(Calendar.getInstance(),
        Calendar.getInstance(), PermissionType.read(), user);

    board.permissions().add(userPermission);

    ctrl.removePermission(board.identity(), user.username());

    boolean found = StreamSupport.stream(board.permissions().spliterator(), false).anyMatch(p -> {
      return p.user().username().equals(user.username());
    });

    assertFalse(found);
  }

  /**
   * Test if passing an unknown entity (not saved in the repository) throws an error in methods.
   */
  @Test
  public void testPassingUnknownEntitiesThrowsError() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> {
      ctrl.isBoardParticipant(board.identity(), user.username());
    });

    assertThrows(NoSuchElementException.class, () -> {
      ctrl.isBoardOwner(board.identity(), user.username());
    });

    assertThrows(NoSuchElementException.class, () -> {
      ctrl.isBoardArchived(board.identity());
    });

    assertThrows(NoSuchElementException.class, () -> {
      ctrl.getUserPermission(board.identity(), user.username());
    });

    assertThrows(NoSuchElementException.class, () -> {
      ctrl.addPermission(board.identity(), user.username(), PermissionType.read());
    });

    assertThrows(NoSuchElementException.class, () -> {
      ctrl.updatePermission(board.identity(), user.username(), PermissionType.read());
    });

    assertThrows(NoSuchElementException.class, () -> {
      ctrl.removePermission(board.identity(), user.username());
    });
  }
}
