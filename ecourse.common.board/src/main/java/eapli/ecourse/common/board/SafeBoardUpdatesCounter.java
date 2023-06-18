package eapli.ecourse.common.board;

import java.util.HashMap;
import java.util.Map;

public class SafeBoardUpdatesCounter {
  private long totalUpdates;
  private BoardUpdatesShared shared;

  /**
   * Map of updates by thread. Each thread has a map of updates by type.
   * This way we can keep track of the number of updates made by each thread.
   */
  private Map<String, Map<String, Long>> updatesByThreads = new HashMap<>();
  private Map<String, Long> updatesByType = new HashMap<>();

  private final String POST_IT_UPDATE = "Post-It updates:";
  private final String POST_IT_CREATION = "Post-It creations:";
  private final String POST_IT_DELETION = "Post-It deletions:";
  private final String BOARD_ARCHIVATION = "Board archivations:";

  public SafeBoardUpdatesCounter(BoardUpdatesShared shared) {
    this.totalUpdates = 0;
    this.shared = shared;
  }

  public synchronized void incrementNumberPostIts(String thread) {
    incrementCount(updatesByThreads, thread, POST_IT_CREATION);
    incrementCount(updatesByType, POST_IT_CREATION);
    this.totalUpdates++;
    shared.write(getNumberPostIts(thread), getUpdates(), POST_IT_CREATION);
  }

  public synchronized void incrementNumberUpdatesPostIts(String thread) {
    incrementCount(updatesByThreads, thread, POST_IT_UPDATE);
    incrementCount(updatesByType, POST_IT_UPDATE);
    this.totalUpdates++;
    shared.write(getNumberUpdatesPostIts(thread), getUpdates(), POST_IT_UPDATE);
  }

  public synchronized void incrementNumberDeletesPostIts(String thread) {
    incrementCount(updatesByThreads, thread, POST_IT_DELETION);
    incrementCount(updatesByType, POST_IT_DELETION);
    this.totalUpdates++;
    shared.write(getNumberDeletesPostIts(thread), getUpdates(), POST_IT_DELETION);
  }

  public synchronized void incrementNumberArchivedBoards(String thread) {
    incrementCount(updatesByThreads, thread, BOARD_ARCHIVATION);
    incrementCount(updatesByType, BOARD_ARCHIVATION);
    this.totalUpdates++;
    shared.write(getNumberArchivedBoards(thread), getUpdates(), BOARD_ARCHIVATION);
  }

  public synchronized long getUpdates() {
    return this.totalUpdates;
  }

  /**
   * Since these methods are being called from a synchronized method, they don't
   * need to be synchronized.
   */

  private void incrementCount(Map<String, Map<String, Long>> updatesByThreads, String threadName, String type) {
    updatesByThreads.putIfAbsent(threadName, new HashMap<>());
    Map<String, Long> map = updatesByThreads.get(threadName);
    map.put(type, map.getOrDefault(type, 0L) + 1);
  }

  private void incrementCount(Map<String, Long> updatesByType, String type) {
    updatesByType.put(type, updatesByType.getOrDefault(type, 0L) + 1);
  }

  private long getCount(Map<String, Map<String, Long>> updatesByThreads, String threadName, String type) {
    Map<String, Long> map = updatesByThreads.get(threadName);
    if (map != null) {
      return map.getOrDefault(type, 0L);
    }
    return 0L;
  }

  private long getCount(Map<String, Long> updatesByType, String type) {
    return updatesByType.getOrDefault(type, 0L);
  }

  /**
   * Getters for the number of updates by type.
   */

  public synchronized long getNumberPostIts(String thread) {
    return getCount(updatesByThreads, thread, POST_IT_CREATION);
  }

  public synchronized long getNumberUpdatesPostIts(String thread) {
    return getCount(updatesByThreads, thread, POST_IT_UPDATE);
  }

  public synchronized long getNumberDeletesPostIts(String thread) {
    return getCount(updatesByThreads, thread, POST_IT_DELETION);
  }

  public synchronized long getNumberArchivedBoards(String thread) {
    return getCount(updatesByThreads, thread, BOARD_ARCHIVATION);
  }

  public synchronized long getTotalUpdatesByType(String updateType) {
    return getCount(updatesByType, updateType);
  }
}
