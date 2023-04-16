package eapli.ecourse.infrastructure.auth;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import eapli.ecourse.Application;
import eapli.framework.util.Utility;

@Utility
public class PasswordEncoderContext {
  private static final Logger LOGGER = LogManager.getLogger(PasswordEncoderContext.class);
  private static PasswordEncoder theFactory;

  private PasswordEncoderContext() {
    // ensure utility
  }

  /**
   * Returns the abstract repository factory configured in the application
   * settings
   *
   * @return the repository factory
   */
  public static PasswordEncoder passwordHash() {
    if (theFactory == null) {
      final String factoryClassName = Application.settings().passwordEncoder();
      try {
        theFactory = (PasswordEncoder) Class.forName(factoryClassName).getDeclaredConstructor()
            .newInstance();
      } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
          | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
          | SecurityException ex) {
        LOGGER.error("Unable to dynamically load the Password Encoder Factory", ex);
        throw new IllegalStateException(
            "Unable to dynamically load the Password Encoder Factory: " + factoryClassName, ex);
      }
    }
    return theFactory;
  }
}
