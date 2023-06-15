package eapli.ecourse.common.board.protocol;

import java.util.HashMap;
import java.util.Map;

public enum MessageCode {
  COMMTEST((byte) 0), DISCONN((byte) 1), ACK((byte) 2), ERR((byte) 3), AUTH((byte) 4), SPLIT(
      (byte) 5), GET_BOARDS((byte) 6), GET_OWN_BOARDS((byte) 7), GET_WRITABLE_BOARDS(
          (byte) 8), GET_BOARD((byte) 9), GET_USER_PERMISSIONS((byte) 10), GET_BOARD_HISTORY(
              (byte) 11), SHARE_BOARD((byte) 12), ARCHIVE_BOARD((byte) 13), CREATE_POSTIT(
                  (byte) 14), EDIT_POSTIT((byte) 15), UNDO_POSTIT((byte) 16), GET_BOARD_POSTITS(
                      (byte) 17), GET_BOARD_OWN_POSTITS((byte) 18), GET_ONLINE_COUNT((byte) 19);

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

  public static MessageCode valueOf(int code) {
    return CODES.get((byte) code);
  }
}
