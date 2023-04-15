package eapli.ecourse.infrastructure.smoketests.backoffice;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// import eapli.ecourse.infrastructure.bootstrapers.TestDataConstants;
// import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.actions.TimedActions;
// import eapli.framework.util.Collections;

/**
 * Simulate a lot of concurrent users fetching data and check how the connection pooling handles it.
 *
 * @author Paulo Gandra Sousa 11/06/2021
 */
public class ConnectionPoolingSmokeTester implements Action {
  private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolingSmokeTester.class);

  private static final AtomicInteger COUNT_OF_ERRORS = new AtomicInteger();
  private static final AtomicInteger COUNT_OF_SUCESSFUL = new AtomicInteger();

  /**
   * @param repo
   * @param prefix
   */
  // private static void doSmokeTest(final BookingRepository repo, final String prefix) {
  // LOGGER.info("{} Thread {} requesting data using instance {}", prefix,
  // Thread.currentThread().getName(), repo);
  // try {
  // // find something
  // TimedActions.delay(500);
  // final var a = repo.findAll();
  // LOGGER.debug("{} Thread {} got data: {} / {}", prefix, Thread.currentThread().getName(),
  // Collections.sizeOf(r), Collections.sizeOf(a));
  // COUNT_OF_SUCESSFUL.incrementAndGet();
  // } catch (final Exception e) {
  // LOGGER.error("Possibly the connection pool is exausted...", e);
  // COUNT_OF_ERRORS.incrementAndGet();
  // }
  // }

  /**
   * All variables are declared locally in the run method. so when the run method ends, there is
   * nothing holding the thread from ending and being cleanup by the JVM.
   *
   * @author Paulo Gandra Sousa 11/06/2021
   */
  private static class LocalVariablesTesterThread extends Thread {
    @Override
    public void run() {
      // doSmokeTest(PersistenceContext.repositories().bookings(), "Local");
    }
  }

  /**
   * The repository is hold on a class member variable. so when the run method ends, there is
   * nothing holding the thread from ending and being cleanup by the JVM.
   *
   * @author Paulo Gandra Sousa 11/06/2021
   */
  private static class ClassVariablesTesterThread extends Thread {
    // private final BookingRepository repo = PersistenceContext.repositories().bookings();

    // @Override
    // public void run() {
    // doSmokeTest(repo, "Class");
    // }
  }

  @Override
  public boolean execute() {
    final var NTHREADS = 1000;

    // helper debug - SHOULD NOT BE USED IN PRODUCTION CODE!!!
    int initialThreadCount = 0;
    if (LOGGER.isDebugEnabled()) {
      initialThreadCount = Thread.activeCount();
      LOGGER.debug("Starting thread tester - initial thread count: {}", initialThreadCount);
    }

    // create threads
    for (var i = 0; i < NTHREADS; i++) {
      if (i % 2 == 0) {
        LOGGER.info("Starting thread - local variables");
        new LocalVariablesTesterThread().start();
      } else {
        LOGGER.info("Starting threads - class variables");
        new ClassVariablesTesterThread().start();
      }
    }
    LOGGER.info("Started {} threads", NTHREADS);

    // Let's wait a while and check
    while (NTHREADS > COUNT_OF_ERRORS.get() + COUNT_OF_SUCESSFUL.get()) {
      TimedActions.delay(500);
    }
    LOGGER.info("In the end we had {} sucessful calls and {} errors", COUNT_OF_SUCESSFUL.get(),
        COUNT_OF_ERRORS.get());

    // helper debug - SHOULD NOT BE USED IN PRODUCTION CODE!!!
    if (LOGGER.isDebugEnabled()) {
      final int finalThreadCount = Thread.activeCount();
      LOGGER.debug("Ending thread tester - final thread count: {} (initially were {})",
          finalThreadCount, initialThreadCount);
      final Thread[] t = new Thread[finalThreadCount];
      final int n = Thread.enumerate(t);
      for (var i = 0; i < n; i++) {
        LOGGER.debug("T {}: {}", t[i].getId(), t[i].getName());
      }
    }

    return true;
  }

}
