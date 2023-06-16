package eapli.ecourse.common.board;

public class OnlineSafeShared {

  public synchronized void read() {
    while (true) {
      try {
        wait();
      } catch (InterruptedException e) {
        // unused
      }
    }
  }

  public synchronized void write(long number) {
    notifyAll();
    System.out.println("There are " + number + " clients online!");
  }
}
