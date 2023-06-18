package eapli.ecourse.postitmanagement.domain;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardBuilder;
import eapli.ecourse.postitmanagement.domain.PostItState.State;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class PostItTest {

  SystemUser u;
  Board b;

  @Before
  public void setUp() {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    this.u = userBuilder.with("username", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(ClientRoles.STUDENT)
        .build();

    BoardBuilder boardBuilder = new BoardBuilder();

    boardBuilder.withColumn("title", 1);
    boardBuilder.withRow("title", 2);
    boardBuilder.withPermissions(new ArrayList<>());
    boardBuilder.withTitle("title");
    boardBuilder.withUser(this.u);

    this.b = boardBuilder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensurePostItNeedsTitle() {
    new PostIt(null, Coordinates.valueOf(1, 1), b, u);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensurePostItNeedsCoordinates() {
    new PostIt(PostItTitle.valueOf("title"), null, b, u);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensurePostItNeedsBoard() {
    new PostIt(PostItTitle.valueOf("title"), Coordinates.valueOf(1, 1), null, u);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensurePostItNeedsUser() {
    new PostIt(PostItTitle.valueOf("title"), Coordinates.valueOf(1, 1), b, null);
  }

  @Test
  public void ensurePostItCanBeCreated() {
    System.out.println(u);

    new PostIt(PostItTitle.valueOf("title"), Coordinates.valueOf(1, 1), b, u);
  }

  @Test
  public void ensureUpdateToggleIsLatest() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt postIt = new PostIt(t, c, b, u);

    assert (postIt.isLatest());
    postIt.update(t, c, PostItDescription.valueOf("newDescription"), null);
    assert (!postIt.isLatest());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureUpdateNeedsTitle() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt postIt = new PostIt(t, c, b, u);

    postIt.update(null, c, PostItDescription.valueOf("newDescription"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureUpdateNeedsCoordinates() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt postIt = new PostIt(t, c, b, u);

    postIt.update(t, null, PostItDescription.valueOf("newDescription"), null);
  }

  @Test
  public void ensureUpdateReturnsCorrectPostIt() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt postIt = new PostIt(t, c, b, u);

    PostIt updatedPostIt = postIt.update(t, c, PostItDescription.valueOf("newDescription"), null);

    assert (updatedPostIt.isLatest());
  }

  @Test
  public void ensureDeleteToggleIsLatest() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt postIt = new PostIt(t, c, b, u);

    assert (postIt.isLatest());
    postIt.delete();
    assert (!postIt.isLatest());
  }

  @Test
  public void ensureDeleteReturnsCorrectPostIt() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt postIt = new PostIt(t, c, b, u);

    PostIt deletedPostIt = postIt.delete();

    assert (deletedPostIt.isLatest());
  }

  @Test
  public void ensureAllConstructorsWork() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt p1 = new PostIt(t, c, b, u);
    new PostIt(t, c, b, u, PostItDescription.valueOf("description"));
    new PostIt(t, c, b, u, PostItDescription.valueOf("description"), PostItImage.valueOf("image"));
    new PostIt(t, c, b, u, PostItImage.valueOf("image"));

    new PostIt(t, c, b, u, false, p1);
    new PostIt(t, c, b, u, false);
  }

  @Test
  public void ensureEqualsWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt p1 = new PostIt(t, c, b, u);

    assert (p1.equals(p1));
  }

  @Test
  public void ensureHashCodeWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt p1 = new PostIt(t, c, b, u);
    PostIt p2 = new PostIt(t, c, b, u);

    assert (p1.hashCode() != p2.hashCode());
  }

  @Test
  public void ensuereOwnerWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt p1 = new PostIt(t, c, b, u);

    assert (p1.owner().equals(u));
  }

  @Test
  public void ensureBoardWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt p1 = new PostIt(t, c, b, u);

    assert (p1.board().equals(b));
  }

  @Test
  public void ensureTitleWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);

    PostIt p1 = new PostIt(t, c, b, u);

    assert (p1.title().equals(t));
  }

  @Test
  public void ensureDescriptionWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItDescription d = PostItDescription.valueOf("description");

    PostIt p1 = new PostIt(t, c, b, u, d);

    assert (p1.description().equals(d));
  }

  @Test
  public void ensureImageWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItImage i = PostItImage.valueOf("image");

    PostIt p1 = new PostIt(t, c, b, u, i);

    assert (p1.image().equals(i));
  }

  @Test
  public void ensureCoordinatesWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItImage i = PostItImage.valueOf("image");

    PostIt p1 = new PostIt(t, c, b, u, i);

    assert (p1.coordinates().equals(c));
  }

  @Test
  public void ensureIsLatestWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItImage i = PostItImage.valueOf("image");

    PostIt p1 = new PostIt(t, c, b, u, i);

    assert (p1.isLatest());
  }

  @Test
  public void ensureIsDeletedWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItImage i = PostItImage.valueOf("image");

    PostIt p1 = new PostIt(t, c, b, u, i);

    assert (!p1.isDeleted());
  }

  @Test
  public void ensureIsActiveWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItImage i = PostItImage.valueOf("image");

    PostIt p1 = new PostIt(t, c, b, u, i);

    assert (p1.isActive());
  }

  @Test
  public void ensureStateWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItImage i = PostItImage.valueOf("image");

    PostIt p1 = new PostIt(t, c, b, u, i);

    assert (p1.state().equals(new PostItState(State.ACTIVE)));
  }

  @Test
  public void ensureSameAsWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItDescription d = PostItDescription.valueOf("description");
    PostItImage i = PostItImage.valueOf("image");

    PostIt p1 = new PostIt(t, c, b, u, d, i);

    assert (p1.sameAs(p1));
  }

  @Test
  public void ensureToggleIsLatestWorks() {
    PostItTitle t = PostItTitle.valueOf("title");
    Coordinates c = Coordinates.valueOf(1, 1);
    PostItDescription d = PostItDescription.valueOf("description");
    PostItImage i = PostItImage.valueOf("image");

    PostIt p1 = new PostIt(t, c, b, u, d, i);
    assert (p1.isLatest());

    p1.toggleIsLatest();

    assert (!p1.isLatest());
  }

}
