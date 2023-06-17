package eapli.ecourse.common.board.protocol;

import java.util.HashMap;
import java.util.Map;

public enum MessageCode {
  COMMTEST((byte) 0), DISCONN((byte) 1), ACK((byte) 2), ERR((byte) 3), AUTH((byte) 4), SPLIT(
      (byte) 5), NOT_MODIFIED((byte) 6), LOGOUT((byte) 7), GET_BOARDS((byte) 8), GET_OWN_BOARDS(
          (byte) 9), GET_WRITABLE_BOARDS((byte) 10), GET_BOARD((byte) 11), GET_USER_PERMISSIONS(
              (byte) 12), SHARE_BOARD((byte) 13), ARCHIVE_BOARD((byte) 14), GET_POSTITS_BOARD(
                  (byte) 15), GET_OWN_POSTITS_BOARD((byte) 16), GET_BOARD_HISTORY(
                      (byte) 17), CREATE_POSTIT((byte) 18), CHANGE_POSTIT((byte) 19), UNDO_POSTIT(
                          (byte) 20), DELETE_POSTIT(
                              (byte) 21), IS_CELL_AVAILABLE((byte) 22), GET_ONLINE_COUNT((byte) 23);

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
