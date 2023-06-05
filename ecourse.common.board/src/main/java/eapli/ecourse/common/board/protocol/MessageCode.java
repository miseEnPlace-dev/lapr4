package eapli.ecourse.common.board.protocol;

import java.util.HashMap;
import java.util.Map;

public enum MessageCode {
  COMMTEST((byte) 0), DISCONN((byte) 1), ACK((byte) 2), ERR((byte) 3), AUTH((byte) 4), GET_BOARDS(
      (byte) 5), GET_OWN_BOARDS((byte) 6), SHARE_BOARD((byte) 7);

  private static final Map<Byte, MessageCode> CODES = new HashMap<>();

  public final byte code;

  private MessageCode(byte code) {
    this.code = code;
  }

  public byte toByte() {
    return this.code;
  }

  static {
    for (MessageCode c : values()) {
      CODES.put(c.code, c);
    }
  }

  public static MessageCode valueOf(byte code) {
    return CODES.get(code);
  }

}
