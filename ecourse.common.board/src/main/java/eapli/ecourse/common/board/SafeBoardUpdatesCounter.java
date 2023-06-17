package eapli.ecourse.common.board;

public class SafeBoardUpdatesCounter {
  private long count;
  private SafeBoardUpdatesShared shared;

  public SafeBoardUpdatesCounter(SafeBoardUpdatesShared shared) {
    this.count = 0;
    this.shared = shared;
  }

  public synchronized void increment() {
    this.count++;
    shared.write(getUpdates());
  }

  public synchronized long getUpdates() {
    return this.count;
  }
}
