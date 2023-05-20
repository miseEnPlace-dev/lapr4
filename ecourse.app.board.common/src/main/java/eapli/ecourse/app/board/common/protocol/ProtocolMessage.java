package eapli.ecourse.app.board.common.protocol;

import java.io.DataInputStream;
import java.io.IOException;

public class ProtocolMessage {
  private static final byte PROTOCOL_VERSION = 1;
  private static final byte METADATA_LENGTH = 4;

  // metadata
  private byte protocolVersion;
  private MessageCode code;
  private int payloadLength;

  private byte[] payload;

  public ProtocolMessage(MessageCode code) {
    this.protocolVersion = PROTOCOL_VERSION;
    this.code = code;
    this.payloadLength = 0;
    this.payload = new byte[0];
  }

  public ProtocolMessage(MessageCode code, byte[] payload, int payloadLength) {
    this.protocolVersion = PROTOCOL_VERSION;
    this.code = code;
    this.payloadLength = payloadLength;
    this.payload = payload;
  }

  private ProtocolMessage(byte protocolVersion, MessageCode code, byte[] payload,
      int payloadLength) {
    this.protocolVersion = protocolVersion;
    this.code = code;
    this.payloadLength = payloadLength;
    this.payload = payload;
  }

  public static ProtocolMessage fromDataStream(DataInputStream input)
      throws IOException, UnsupportedVersionException {
    // according to the protocol message format, the first 4 bytes
    // are the header of the message (metadata)
    byte[] metadata = input.readNBytes(METADATA_LENGTH);

    // the first byte is the protocol version
    byte protocolVersion = metadata[0];

    if (protocolVersion != PROTOCOL_VERSION)
      throw new UnsupportedVersionException("Unsupported protocol version " + protocolVersion);

    // the second byte is the code
    byte rawCode = metadata[1];
    MessageCode code = MessageCode.valueOf(rawCode);

    // the length of the data (d_length_1 + 256 * c d_length_2)
    int payloadLength = metadata[2] + 256 * metadata[3];

    // read the payload
    byte[] payload = input.readNBytes(payloadLength);

    return new ProtocolMessage(protocolVersion, code, payload, payloadLength);
  }

  public byte[] toByteStream() {
    byte[] result = new byte[this.payloadLength + METADATA_LENGTH];

    // version
    result[0] = this.protocolVersion;

    // code
    result[1] = this.code.toByte();

    // data length
    result[2] = (byte) (this.payloadLength % 256);
    result[3] = (byte) (this.payloadLength / 256);

    System.arraycopy(this.payload, 0, result, METADATA_LENGTH, this.payloadLength);

    return result;
  }

  public byte getProtocolVersion() {
    return this.protocolVersion;
  }

  public MessageCode getCode() {
    return this.code;
  }

  public int getPayloadLength() {
    return this.payloadLength;
  }

  public byte[] getPayload() {
    return this.payload;
  }

  public String toString() {
    return String.format(" --- ProtocolMessage version %d ---\n%s request, payload length: %d%s",
        this.protocolVersion, this.code.toString(), this.payloadLength,
        this.payloadLength != 0 ? "\nPayload: " + new String(this.payload) : "");
  }
}
