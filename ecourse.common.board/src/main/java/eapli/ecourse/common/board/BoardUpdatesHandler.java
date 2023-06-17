package eapli.ecourse.common.board;

public class BoardUpdatesHandler implements Runnable {
  private SafeBoardUpdatesShared shared;

  public BoardUpdatesHandler(SafeBoardUpdatesShared shared) {
    this.shared = shared;
  }

  @Override
  public void run() {
    shared.read();
  }

}
