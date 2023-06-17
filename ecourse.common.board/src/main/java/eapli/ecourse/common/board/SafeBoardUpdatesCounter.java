package eapli.ecourse.common.board;

public class SafeBoardUpdatesCounter {
  private long nPostIts;
  private long nUpdatesPostIts;
  private long nDeletesPostIts;
  private long nArchivedBoards;
  private long totalUpdates;
  private SafeBoardUpdatesShared shared;

  private final String POST_IT_UPDATE = "Post-It updates:";
  private final String POST_IT_CREATION = "Post-It creations:";
  private final String POST_IT_DELETION = "Post-It deletions:";
  private final String BOARD_ARCHIVATION = "Board archivations:";

  public SafeBoardUpdatesCounter(SafeBoardUpdatesShared shared) {
    this.totalUpdates = 0;
    this.shared = shared;
    this.nPostIts = 0;
    this.nUpdatesPostIts = 0;
    this.nDeletesPostIts = 0;
    this.nArchivedBoards = 0;
  }

  public synchronized void incrementNumberPostIts() {
    this.totalUpdates++;
    this.nPostIts++;
    shared.write(getNumberPostIts(), getUpdates(), POST_IT_CREATION);
  }

  public synchronized void incrementNumberUpdatesPostIts() {
    this.totalUpdates++;
    this.nUpdatesPostIts++;
    shared.write(getNumberUpdatesPostIts(), getUpdates(), POST_IT_UPDATE);
  }

  public synchronized void incrementNumberDeletesPostIts() {
    this.totalUpdates++;
    this.nDeletesPostIts++;
    shared.write(getNumberDeletesPostIts(), getUpdates(), POST_IT_DELETION);
  }

  public synchronized void incrementNumberArchivedBoards() {
    this.totalUpdates++;
    this.nArchivedBoards++;
    shared.write(getNumberArchivedBoards(), getUpdates(), BOARD_ARCHIVATION);
  }

  public synchronized long getUpdates() {
    return this.totalUpdates;
  }

  public synchronized long getNumberPostIts() {
    return this.nPostIts;
  }

  public synchronized long getNumberUpdatesPostIts() {
    return this.nUpdatesPostIts;
  }

  public synchronized long getNumberDeletesPostIts() {
    return this.nDeletesPostIts;
  }

  public synchronized long getNumberArchivedBoards() {
    return this.nArchivedBoards;
  }
}
