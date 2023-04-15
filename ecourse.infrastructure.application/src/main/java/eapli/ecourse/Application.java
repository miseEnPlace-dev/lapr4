package eapli.ecourse;

import eapli.framework.util.Utility;

/**
 * A "global" static class with the application registry of well known objects.
 *
 * @author Paulo Gandra Sousa
 */
@Utility
public class Application {

  public static final String VERSION = "v4 (Not using a IoC container)";
  public static final String COPYRIGHT = "(C) 2016-2023, ISEP's Professors of EAPLI";

  private static final AppSettings SETTINGS = new AppSettings();

  public static AppSettings settings() {
    return SETTINGS;
  }

  private Application() {
    // private visibility to ensure singleton & utility
  }
}
