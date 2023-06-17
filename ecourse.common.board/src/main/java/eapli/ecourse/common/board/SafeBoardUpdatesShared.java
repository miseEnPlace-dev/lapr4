package eapli.ecourse.common.board;

public class SafeBoardUpdatesShared {
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

  public synchronized void write(long number, long totalUpdates, String type) {
    this.signaled = true;
    updateMessage = String.format("\n[%s] Just made an update!\n%s %d \nTotal number of updates: %d",
        Thread.currentThread().getName(),
        type, number, totalUpdates);
    notifyAll();
  }

  public synchronized String getUpdateMessage() {
    return updateMessage;
  }
}
