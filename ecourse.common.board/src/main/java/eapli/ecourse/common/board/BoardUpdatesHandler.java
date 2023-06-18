package eapli.ecourse.common.board;

public class BoardUpdatesHandler implements Runnable {
  private BoardUpdatesShared shared;

  public BoardUpdatesHandler(BoardUpdatesShared shared) {
    this.shared = shared;
  }

  @Override
  public void run() {
    shared.read();
  }
}
