package eapli.ecourse.postitmanagement.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class UndoPostItControllerTest extends PostItControllerBaseTest {
  private BoardRepository boardRepository;
  private PostItRepository postItRepository;
  private UndoPostItController ctrl;
  private UserManagementService usrSvc;

  @Before
  public void setup() {
    this.boardRepository = mock(BoardRepository.class);
    this.postItRepository = mock(PostItRepository.class);
    this.usrSvc = mock(UserManagementService.class);

    TransactionalContext tx = mock(TransactionalContext.class);

    this.ctrl = new UndoPostItController(tx, postItRepository);
  }

  @Test
  public void testPostItExists() {
    PostIt postIt = getDummyPostIt();

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));

    boolean result = ctrl.postItExists(postIt.identity());

    assertTrue(result);
  }

  @Test
  public void testPostItDoesNotExist() {
    PostIt postIt = getDummyPostIt();

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.empty());

    boolean result = ctrl.postItExists(postIt.identity());

    assertFalse(result);
  }

  @Test
  public void testCanEditPostItAsOwner() {
    PostIt postIt = getDummyPostIt();
    SystemUser user = getNewDummyUser();
    assertTrue(postIt.owner().hasIdentity(user.username()));

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));
    when(usrSvc.activateUser(user)).thenReturn(user);

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));

    boolean result = ctrl.canEditPostIt(postIt.identity(), user.username());

    assertTrue(result);
  }

  @Test
  public void testCanEditPostItAsBoardOwner() {
    PostIt postIt = getDummyPostIt(); // owner: user1

    SystemUser user = getNewDummyUser();

    Board board = getDummyBoard();

    when(boardRepository.ofIdentity(postIt.board().identity())).thenReturn(Optional.of(board));
    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));
    when(usrSvc.activateUser(user)).thenReturn(user);

    boolean result = ctrl.canEditPostIt(postIt.identity(), user.username());

    assertTrue(result);
  }

  @Test
  public void testCannotEditPostIt() {
    PostIt postIt = getDummyPostIt2(); // owner: user2

    SystemUser user = getNewDummyUser();

    Board board = getDummyBoard();

    when(boardRepository.ofIdentity(postIt.board().identity())).thenReturn(Optional.of(board));
    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));
    when(usrSvc.activateUser(user)).thenReturn(user);

    boolean result = ctrl.canEditPostIt(postIt.identity(), user.username());

    assertFalse(result);
  }
}
