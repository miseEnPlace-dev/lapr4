package eapli.ecourse.infrastructure.persistence;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.Application;
import eapli.framework.util.Utility;

/**
 * Provides easy access to the persistence layer. Works as a factory of factories.
 *
 * @author Paulo Gandra Sousa
 */
@Utility
public final class PersistenceContext {
  private static final Logger LOGGER = LogManager.getLogger(PersistenceContext.class);
  private static RepositoryFactory theFactory;

  private PersistenceContext() {
    // ensure utility
  }

  /**
   * Returns the abstract repository factory configured in the application settings
   *
   * @return the repository factory
   */
  public static RepositoryFactory repositories() {
    if (theFactory == null) {
      final String factoryClassName = Application.settings().repositoryFactory();
      try {
        theFactory = (RepositoryFactory) Class.forName(factoryClassName).getDeclaredConstructor()
            .newInstance();
      } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
          | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
          | SecurityException ex) {
        LOGGER.error("Unable to dynamically load the Repository Factory", ex);
        throw new IllegalStateException(
            "Unable to dynamically load the Repository Factory: " + factoryClassName, ex);
      }
    }
    return theFactory;
  }
}
