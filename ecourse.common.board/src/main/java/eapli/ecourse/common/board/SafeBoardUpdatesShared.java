package eapli.ecourse.common.board;

public class SafeBoardUpdatesShared {
  private long number = 0;
  private boolean signaled = false;

  public synchronized void read() {
    while (!signaled) {
      try {
        wait();
        System.out.printf("\n[Thread: %s] Board was just updated!\n Total updates: %d !",
            Thread.currentThread().getName(),
            this.number);
      } catch (InterruptedException e) {
        // unused
      }
      signaled = false;
    }
  }

  public synchronized void write(long number) {
    this.number = number;
    this.signaled = true;
    notifyAll();
  }

}
