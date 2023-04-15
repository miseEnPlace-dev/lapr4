package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.infrastructure.bootstrapers.ECourseBootstrapper;
import eapli.ecourse.infrastructure.bootstrapers.demo.ECourseDemoBootstrapper;

/**
 * A static initialiser to make sure there is the default bootstrapping data, namely power user,
 * when using the in memory repositories.
 * <p>
 * Since we are using an in memory implementation of the persistence, the bootstrap data will be
 * lost at the end of executing the bootstrap application. This helper class initialises the same
 * data as the bootstrapper in the other applications.
 * <p>
 * Note that this is just for demo purposes and in a real scenario you would use a persistent
 * mechanism like a database or file system in order not to loose data.
 * <p>
 *
 * Each repository must call this static initialiser, e.g.:
 *
 * <pre>
 * <code>
 * public class InMemoryMaterialRepository extends InMemoryDomainRepository<String, Material>
 *       implements MaterialRepository {
 *
 *    static {
 *      InMemoryInitializer.init();
 *    }
 * }
 * </code>
 * </pre>
 *
 * @author Paulo Gandra de Sousa
 */
final class InMemoryInitializer {

  private static class LazyHolder {
    private static final InMemoryInitializer INSTANCE = new InMemoryInitializer();

    private LazyHolder() {}
  }

  private boolean initialized;

  private InMemoryInitializer() {
    // ensure no public instantiation
  }

  private void initialize() {
    if (!initialized) {
      // to ensure some default test data is available, specially when using
      // in memory persistence
      new ECourseBootstrapper().execute();
      new ECourseDemoBootstrapper().execute();
      initialized = true;
    }
  }

  public static void init() {
    LazyHolder.INSTANCE.initialize();
  }
}
