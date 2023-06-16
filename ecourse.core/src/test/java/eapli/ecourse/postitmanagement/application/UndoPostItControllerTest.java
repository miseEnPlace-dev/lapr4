package eapli.ecourse.postitmanagement.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class UndoPostItControllerTest extends PostItControllerBaseTest {
  private PostItRepository postItRepository;
  private UndoPostItController ctrl;
  private TransactionalContext tx;

  @Before
  public void setup() {
    this.postItRepository = mock(PostItRepository.class);
    this.tx = mock(TransactionalContext.class);

    this.ctrl = new UndoPostItController(tx, postItRepository);
  }

  /**
   * Test if a Post-It exists.
   */
  @Test
  public void testPostItExists() {
    PostIt postIt = getDummyPostIt();

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));

    boolean result = ctrl.postItExists(postIt.identity());
    assertTrue(result);
  }

  /**
   * Test if a Post-It does not exist.
   */
  @Test
  public void testPostItDoesNotExist() {
    PostIt postIt = getDummyPostIt();

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.empty());

    boolean result = ctrl.postItExists(postIt.identity());
    assertFalse(result);
  }

  /**
   * Test if a Post-It can be edited by its owner.
   */
  @Test
  public void testCanEditPostItAsOwner() {
    PostIt postIt = getDummyPostIt(); // owner: user1
    SystemUser user = getNewDummyUser();

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));

    boolean result = ctrl.canEditPostIt(postIt.identity(), user.username());
    assertTrue(result);
  }

  /**
   * Test if a Post-It cannot be edited by another user.
   */
  @Test
  public void testCannotEditPostIt() {
    PostIt postIt = getDummyPostIt2(); // owner: user2
    SystemUser user = getNewDummyUser();

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));

    boolean result = ctrl.canEditPostIt(postIt.identity(), user.username());
    assertFalse(result);
  }

  /**
   * Test if undoing a Post-It works.
   */
  @Test
  public void testUndoPostIt() {
    PostIt postIt = getDummyPostItWithPrevious();
    PostIt previous = postIt.previous();

    when(postItRepository.ofIdentity(previous.identity())).thenReturn(Optional.of(previous));
    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));
    when(postItRepository.save(previous)).thenReturn(previous);

    assertFalse(previous.isLatest());

    ctrl.undoPostIt(postIt.identity());

    // a transaction is started & committed
    verify(tx, times(1)).beginTransaction();
    verify(tx, times(1)).commit();

    // main post-it gets deleted
    verify(postItRepository, times(1)).delete(postIt);

    // the previous is now the latest
    boolean result = previous.isLatest();
    assertTrue(result);
  }

  /**
   * Test if undoing a Post-It that is not the latest fails.
   */
  @Test
  public void testUndoNotLatestPostIt() {
    PostIt postIt = getDummyPostItNotLatest();

    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));

    assertThrows(IllegalStateException.class, () -> {
      ctrl.undoPostIt(postIt.identity());
    });
  }

  /**
   * Test if undoing a Post-It that does not have a previous version fails.
   */
  @Test
  public void testPreviousPostItDoesNotExistWhenUndoing() {
    PostIt postIt = getDummyPostItWithPrevious();
    PostIt previous = postIt.previous();

    when(postItRepository.ofIdentity(previous.identity())).thenReturn(Optional.of(previous));
    when(postItRepository.ofIdentity(postIt.identity())).thenReturn(Optional.of(postIt));
    when(postItRepository.save(previous)).thenReturn(previous);

    assertThrows(IllegalStateException.class, () -> {
      ctrl.undoPostIt(previous.identity());
    });
  }
}
