package eapli.ecourse.common.board.protocol;

import java.util.HashMap;
import java.util.Map;

public enum MessageCode {
  COMMTEST((byte) 0), DISCONN((byte) 1), ACK((byte) 2), ERR((byte) 3), AUTH((byte) 4), SPLIT(
      (byte) 5), NOT_MODIFIED((byte) 6), GET_BOARDS((byte) 7), GET_OWN_BOARDS(
          (byte) 8), GET_WRITABLE_BOARDS((byte) 9), GET_BOARD((byte) 10), GET_USER_PERMISSIONS(
              (byte) 11), SHARE_BOARD((byte) 12), ARCHIVE_BOARD((byte) 13), GET_POSTITS_BOARD(
                  (byte) 14), GET_OWN_POSTITS_BOARD((byte) 15), GET_BOARD_HISTORY(
                      (byte) 16), CREATE_POSTIT((byte) 17), CHANGE_POSTIT((byte) 18), UNDO_POSTIT(
                          (byte) 19), DELETE_POSTIT(
                              (byte) 20), IS_CELL_AVAILABLE((byte) 21), GET_ONLINE_COUNT((byte) 22);

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
