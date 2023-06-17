package eapli.ecourse.common.board;

public class OnlineSafeShared {
  private long number = 0;
  private boolean signaled = false;

  public synchronized void read() {
    while (true) {
      try {
        wait();
        if (signaled) {
          System.out.println("There are " + this.number + " clients online!");
          signaled = false;
        }
      } catch (InterruptedException e) {
        // unused
      }
    }
  }

  public synchronized void write(long number) {
    this.number = number;
    this.signaled = true;
    notifyAll();
  }
}
