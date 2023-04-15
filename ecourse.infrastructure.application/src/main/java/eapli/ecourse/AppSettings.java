package eapli.ecourse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {
  private static final Logger LOGGER = LogManager.getLogger(AppSettings.class);

  private static final String PROPERTIES_RESOURCE = "application.properties";
  private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
  private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
  private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
  private static final String SCHEMA_GENERATION_KEY =
      "javax.persistence.schema-generation.database.action";
  private static final String HIGH_CALORIES_DISH_LIMIT = "HighCaloriesDishLimit";
  private static final String USE_EVENTFUL_CONTROLLERS = "UseEventfulControllers";

  private final Properties applicationProperties = new Properties();

  public AppSettings() {
    loadProperties();
  }

  private void loadProperties() {
    try (InputStream propertiesStream =
        this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_RESOURCE)) {
      if (propertiesStream == null) {
        throw new FileNotFoundException(
            "Property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
      }
      applicationProperties.load(propertiesStream);
    } catch (final IOException exio) {
      setDefaultProperties();

      LOGGER.warn("Loading default properties", exio);
    }
  }

  private void setDefaultProperties() {
    applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
        "eapli.ecourse.persistence.jpa.JpaRepositoryFactory");
    applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
    applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli.eCoursePU");
    applicationProperties.setProperty(HIGH_CALORIES_DISH_LIMIT, "300");
  }

  public boolean isMenuLayoutHorizontal() {
    return "horizontal"
        .equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
  }

  public String persistenceUnitName() {
    return applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
  }

  public String repositoryFactory() {
    return applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
  }

  public Integer highCaloriesDishLimit() {
    return Integer.valueOf(this.applicationProperties.getProperty(HIGH_CALORIES_DISH_LIMIT));
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public Map extendedPersistenceProperties() {
    final Map ret = new HashMap();
    ret.put(SCHEMA_GENERATION_KEY, applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
    return ret;
  }

  public String getProperty(final String prop) {
    return applicationProperties.getProperty(prop);
  }

  public boolean useEventfulControllers() {
    return Boolean.parseBoolean(applicationProperties.getProperty(USE_EVENTFUL_CONTROLLERS));
  }
}
