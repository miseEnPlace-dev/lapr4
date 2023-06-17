package eapli.ecourse.common.board;

public class SafeBoardUpdatesShared {
  private long number = 0;

  public synchronized void read() {
    while (true) {
      try {
        wait();
        System.out.printf("\n[Thread: %s] Board was just updated!\n Total updates: %d !",
            Thread.currentThread().getName(),
            this.number);
      } catch (InterruptedException e) {
        // unused
      }
    }
  }

  public synchronized void write(long number) {
    this.number = number;
    notifyAll();
  }

}
