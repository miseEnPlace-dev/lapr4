package eapli.ecourse.app.board.common.protocol;

public class UnsupportedVersionException extends Exception {
  public UnsupportedVersionException() {}

  public UnsupportedVersionException(String message) {
    super(message);
  }
}
