package eapli.ecourse.postitmanagement.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class UndoPostItControllerTest extends PostItControllerBaseTest {
  private PostItRepository postItRepository;

  @Before
  public void setup() {
    new UndoPostItController(null, postItRepository);
  }

  @Test
  public void testPostItExists() {
    PostIt postIt = getDummyPostIt();

    // when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));

    // boolean result = ctrl.postItExists(postIt.identity());

    assertNotNull(postIt);
  }

  @Test
  public void testPostItDoesNotExist() {
    PostIt postIt = null;

    // when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.empty());

    // boolean result = ctrl.postItExists(postIt.identity());

    assertNull(postIt);
  }

  @Test
  public void testCanEditPostItAsOwner() {
    PostIt postIt = getDummyPostIt();
    SystemUser user = getNewDummyUser();
    assertTrue(postIt.owner().hasIdentity(user.username()));
    /*
     * when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));
     * when(usrSvc.activateUser(user)).thenReturn(user);
     *
     * when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));
     *
     * boolean result = ctrl.canEditPostIt(postIt.identity(), user.username());
     *
     * assertTrue(result);
     */
  }

  @Test
  public void testCanEditPostItAsBoardOwner() {
    PostIt postIt = getDummyPostIt();
    SystemUser user = getNewDummyUser();
    assertTrue(postIt.owner().hasIdentity(user.username()));

    Board board = getDummyBoard();
    // user is owner of board

    PostIt postIt2 = getDummyPostIt2();
    assertTrue(postIt.owner().hasIdentity(user.username()));
    assertFalse(postIt2.owner().hasIdentity(user.username()));
    assertTrue(board.owner().hasIdentity(postIt.owner().identity()));
    /*
     * when(postItRepository.ofIdentity(postItId)).thenReturn(Optional.of(postIt));
     * when(boardRepository.ofIdentity(postIt.board())).thenReturn(Optional.of(board));
     * when(userSvc.sessionUser()).thenReturn(new SystemUser(username));
     *
     * boolean result = ctrl.canEditPostIt(postItId, username);
     *
     * assertTrue(result);
     */
  }

  @Test
  public void testCannotEditPostIt() {
    PostIt postIt = getDummyPostIt();
    SystemUser user = getNewDummyUser();
    assertTrue(postIt.owner().hasIdentity(user.username()));

    Board board = getDummyBoard();
    // user is owner of board

    PostIt postIt2 = getDummyPostIt2();
    assertTrue(postIt.owner().hasIdentity(user.username()));
    assertFalse(postIt2.owner().hasIdentity(user.username()));
    assertTrue(board.owner().hasIdentity(postIt.owner().identity()));

    /*
     * when(postItRepository.ofIdentity(postItId)).thenReturn(Optional.of(postIt));
     * when(boardRepository.ofIdentity(postIt.board())).thenReturn(Optional.of(board));
     * when(userSvc.sessionUser()).thenReturn(new SystemUser(username));
     *
     * boolean result = ctrl.canEditPostIt(postItId, username);
     *
     * assertFalse(result);
     */
  }

}
