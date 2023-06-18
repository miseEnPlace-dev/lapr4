package eapli.ecourse.postitmanagement.domain;

import static org.junit.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.Test;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardBuilder;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class PostItBuilderTest {
  @Test
  public void ensurePostItHasTitle() {
    assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withTitle(null));
  }

  @Test
  public void ensurePostItHasTitleAndCoordinates() {
    assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withTitle(null).withCoordinates(0, 0));
  }

  @Test
  public void ensurePostItHasTitleAndBoard() {
    assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withTitle(null).withBoard(null));
  }

  @Test
  public void ensurePostItHasTitleAndUser() {
    assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withTitle(null).withUser(null));
  }

  @Test
  public void ensurePostItHasTitleCoordinatesAndBoard() {
    assertThrows(IllegalArgumentException.class,
        () -> new PostItBuilder().withTitle(null).withCoordinates(0, 0).withBoard(null));
  }

  @Test
  public void ensurePostItHasTitleCoordinatesAndUser() {
    assertThrows(IllegalArgumentException.class,
        () -> new PostItBuilder().withTitle(null).withCoordinates(0, 0).withUser(null));
  }

  @Test
  public void ensureItIsPossibleToCreatePostIt() {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    SystemUser u = userBuilder.with("username", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(ClientRoles.STUDENT)
        .build();

    BoardBuilder boardBuilder = new BoardBuilder();

    boardBuilder.withColumn("title", 1);
    boardBuilder.withRow("title", 2);
    boardBuilder.withPermissions(new ArrayList<>());
    boardBuilder.withTitle("title");
    boardBuilder.withUser(u);

    Board b = boardBuilder.build();
    new PostItBuilder().withTitle("title").withCoordinates(0, 0).withBoard(b).withUser(u);
  }
}
