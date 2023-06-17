package eapli.ecourse.common.board;

public class OnlineSafeShared {
  private long number = 0;
  private boolean signaled = false;

  public synchronized void read() {
    while (!signaled) {
      try {
        wait();
        System.out.println("There are " + this.number + " clients online!");
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
