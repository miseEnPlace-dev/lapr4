package eapli.ecourse.boardmanagement.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Calendar;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.boardmanagement.domain.UserPermission;
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

  @Test
  public void testBoardExists() {
    Board board = getDummyBoard();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    boolean result = ctrl.boardExists(board.identity());

    assertTrue(result);
  }

  @Test
  public void testBoardDoesNotExist() {
    Board board = getDummyBoard();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.empty());

    boolean result = ctrl.boardExists(board.identity());

    assertFalse(result);
  }

  @Test
  public void testIsBoardParticipant() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));
    when(usrSvc.activateUser(user)).thenReturn(user);

    UserPermission userPermission = new UserPermission(Calendar.getInstance(),
        Calendar.getInstance(), PermissionType.read(), user);

    board.permissions().add(userPermission);
    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));

    boolean result = ctrl.isBoardParticipant(board.identity(), user.username());

    assertTrue(result);
  }

  @Test
  public void testIsNotBoardParticipant() {
    Board board = getDummyBoard();
    SystemUser user = getNewDummyUser2();

    when(boardRepository.ofIdentity(board.identity())).thenReturn(Optional.of(board));
    when(usrSvc.activateUser(user)).thenReturn(user);

    boolean result = ctrl.isBoardParticipant(board.identity(), user.username());

    assertFalse(result);
  }
}
