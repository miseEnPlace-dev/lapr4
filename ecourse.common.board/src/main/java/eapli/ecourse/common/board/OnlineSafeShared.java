package eapli.ecourse.common.board;

public class OnlineSafeShared {
  private long number = 0;

  public synchronized void read() {
    while (true) {
      try {
        wait();
        System.out.println("There are " + this.number + " clients online!");
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
