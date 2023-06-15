package eapli.ecourse.common.board;

public class SafeOnlineCounter {
  private long count;

  public SafeOnlineCounter() {
    this.count = 0;
  }

  public synchronized void increment() {
    this.count++;
  }

  public synchronized void decrement() {
    this.count--;
  }

  public synchronized long getOnlineCount() {
    return this.count;
  }
}
