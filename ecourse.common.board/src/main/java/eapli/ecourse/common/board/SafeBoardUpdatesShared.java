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

  public synchronized void printTotalStats(long totalUpdates, long numberPostIts, long numberUpdatesPostIts,
      long numberDeletesPostIts, long numberArchivedBoards) {
    System.out.println("\n[" + Thread.currentThread().getName() + "] Exiting...");
    System.out.println("\nHere are the statistics recorded by this thread:");
    System.out.println("Total number of post-its created: " + numberPostIts);
    System.out.println("Total number of post-its updated: " + numberUpdatesPostIts);
    System.out.println("Total number of post-its deleted: " + numberDeletesPostIts);
    System.out.println("Total number of boards archived: " + numberArchivedBoards);
    System.out.println("\nTotal number of updates made in the server until now: " + totalUpdates);
  }
}
