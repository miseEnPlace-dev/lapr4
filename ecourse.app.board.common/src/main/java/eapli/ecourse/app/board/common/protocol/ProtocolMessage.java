package eapli.ecourse.app.board.common.protocol;

import java.io.DataInputStream;
import java.io.IOException;

public class ProtocolMessage {
  private static final byte PROTOCOL_VERSION = 1;
  private static final byte METADATA_LENGTH = 4;

  private byte protocolVersion;
  private MessageCode code;
  private int dataLength;
  private byte[] data;

  public ProtocolMessage(MessageCode code, byte[] data, int dataLength) {
    this.protocolVersion = PROTOCOL_VERSION;
    this.code = code;
    this.dataLength = dataLength;
    this.data = data;
  }

  private ProtocolMessage(byte protocolVersion, MessageCode code, byte[] data, int dataLength) {
    this.protocolVersion = protocolVersion;
    this.code = code;
    this.dataLength = dataLength;
    this.data = data;
  }

  public static ProtocolMessage fromDataStream(DataInputStream input)
      throws IOException, UnsupportedVersionException {
    // according to the protocol message format, the first 4 bytes
    // contain metadata about the message
    byte[] metadata = input.readNBytes(METADATA_LENGTH);

    // the first byte is the protocol version
    byte protocolVersion = metadata[0];

    if (protocolVersion != PROTOCOL_VERSION)
      throw new UnsupportedVersionException("Invalid version " + protocolVersion);

    // the second byte is the code
    byte rawCode = metadata[1];
    MessageCode code = MessageCode.valueOf(rawCode);

    // the length of the data (d_length_1 + 256 * c d_length_2)
    int dataLength = metadata[2] + 256 * metadata[3];

    // read n bytes from the data field in the protocol
    byte[] data = input.readNBytes(dataLength);

    return new ProtocolMessage(protocolVersion, code, data, dataLength);
  }

  public byte[] toByteStream() {
    byte[] result = new byte[this.dataLength + METADATA_LENGTH];

    // version
    result[0] = this.protocolVersion;

    // code
    result[1] = this.code.toByte();

    // data length
    result[2] = (byte) (this.dataLength % 256);
    result[3] = (byte) (this.dataLength / 256);

    System.arraycopy(this.data, 0, result, METADATA_LENGTH, this.dataLength);

    return result;
  }

  public byte getProtocolVersion() {
    return this.protocolVersion;
  }

  public MessageCode getCode() {
    return this.code;
  }

  public int getDataLength() {
    return this.dataLength;
  }

  public byte[] getData() {
    return this.data;
  }
}
