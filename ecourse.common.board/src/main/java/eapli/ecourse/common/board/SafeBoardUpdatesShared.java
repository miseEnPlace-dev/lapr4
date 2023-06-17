package eapli.ecourse.common.board;

public class SafeBoardUpdatesShared {
  private long number = 0;
  private boolean signaled = false;
  private String updateMessage;

  public synchronized void read() {
    while (!signaled) {
      try {
        wait();
        System.out.println(getUpdateMessage());
      } catch (InterruptedException e) {
        // unused
      }
      signaled = false;
    }
  }

  public synchronized void write(long number) {
    this.number = number;
    this.signaled = true;
    updateMessage = String.format("[Thread: %s] Board was just updated! Total updates: %d !",
        Thread.currentThread().getName(),
        this.number);
    notifyAll();
  }

  public synchronized String getUpdateMessage() {
    return updateMessage;
  }
}
