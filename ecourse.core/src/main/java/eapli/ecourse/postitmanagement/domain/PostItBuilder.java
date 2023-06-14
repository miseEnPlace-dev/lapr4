package eapli.ecourse.postitmanagement.domain;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

public class PostItBuilder implements DomainFactory<PostIt> {
  private PostIt postIt;

  private PostItTitle title;
  private Coordinates coordinates;
  private PostItDescription description;
  private PostItImage image;
  private Board board;
  private SystemUser user;

  public PostItBuilder() {
    this.description = null;
    this.image = null;
  }

  public PostItBuilder withTitle(String title) {
    this.title = PostItTitle.valueOf(title);
    return this;
  }

  public PostItBuilder withCoordinates(int x, int y) {
    this.coordinates = Coordinates.valueOf(x, y);
    return this;
  }

  public PostItBuilder withDescription(String description) {
    this.description = PostItDescription.valueOf(description);
    return this;
  }

  public PostItBuilder withImage(String encodedImage) {
    this.image = PostItImage.valueOf(encodedImage);
    return this;
  }

  public PostItBuilder withBoard(Board board) {
    this.board = board;
    return this;
  }

  public PostItBuilder withUser(SystemUser user) {
    this.user = user;
    return this;
  }

  @Override
  public PostIt build() {
    return this.buildOrThrow();
  }

  private PostIt buildOrThrow() {
    if (postIt != null) {
      return postIt;
    }

    // Only fields that are mandatory are checked
    Preconditions.noneNull(this.title, this.coordinates, this.board, this.user);

    if (this.description == null && this.image == null)
      postIt = new PostIt(title, coordinates, board, user);
    else if (this.image != null && this.description != null)
      postIt = new PostIt(title, coordinates, board, user, description, image);
    else if (this.image != null)
      postIt = new PostIt(title, coordinates, board, user, image);
    else
      postIt = new PostIt(title, coordinates, board, user, description);

    return postIt;
  }

}
