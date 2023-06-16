package eapli.ecourse.common.board;

public class OnlineCheckerHandler implements Runnable {
  private OnlineSafeShared shared;

  public OnlineCheckerHandler(OnlineSafeShared shared) {
    this.shared = shared;
  }

  @Override
  public void run() {
    shared.read();
  }
}
