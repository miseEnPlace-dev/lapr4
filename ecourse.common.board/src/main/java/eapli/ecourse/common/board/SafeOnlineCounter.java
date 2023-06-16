package eapli.ecourse.common.board;

public class SafeOnlineCounter {
  private long count;
  private OnlineSafeShared shared;

  public SafeOnlineCounter(OnlineSafeShared shared) {
    this.count = 0;
    this.shared = shared;
  }

  public synchronized void increment() {
    this.count++;
    if (count != 0 && count % 3 == 0)
      shared.write(getOnlineCount());
  }

  public synchronized void decrement() {
    this.count--;
    if (count != 0 && count % 3 == 0)
      shared.write(getOnlineCount());
  }

  public synchronized long getOnlineCount() {
    return this.count;
  }
}
