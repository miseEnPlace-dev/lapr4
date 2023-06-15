package eapli.ecourse.common.board.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ProtocolMessage {
  private static final byte PROTOCOL_VERSION = 1;
  private static final byte METADATA_LENGTH = 4;
  private static final int MAX_PAYLOAD_LENGTH = 256 * 256 - 1; // (2^8) ^ 2 - 1 = max number

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

  public ProtocolMessage(MessageCode code, String payload) {
    this.protocolVersion = PROTOCOL_VERSION;
    this.code = code;
    this.payloadLength = payload.length();
    this.payload = payload.getBytes();
  }

  public ProtocolMessage(MessageCode code, byte[] payload) {
    this.protocolVersion = PROTOCOL_VERSION;
    this.code = code;
    this.payloadLength = payload.length;
    this.payload = payload;
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

  public ProtocolMessage(MessageCode code, Object o) throws IOException {
    this.protocolVersion = PROTOCOL_VERSION;
    this.code = code;

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(bos);
    out.writeObject(o);
    out.flush();

    this.payload = bos.toByteArray();
    bos.close();

    this.payloadLength = this.payload.length;
  }

  public static ProtocolMessage fromDataStream(DataInputStream input)
      throws IOException, UnsupportedVersionException, ClassNotFoundException {
    // according to the protocol message format, the first 4 bytes
    // are the header of the message (metadata)
    byte[] metadata = new byte[METADATA_LENGTH];
    int bytesRead = input.read(metadata, 0, METADATA_LENGTH);

    if (bytesRead == -1)
      return null;

    // the first byte is the protocol version
    byte protocolVersion = metadata[0];

    if (protocolVersion != PROTOCOL_VERSION)
      throw new UnsupportedVersionException("Unsupported protocol version " + protocolVersion);

    // the second byte is the code
    int rawCode = metadata[1] & 0xFF; // & 0xFF to "transform" in an unsigned byte
    MessageCode code = MessageCode.valueOf(rawCode);

    // the length of the data (d_length_1 + 256 * c d_length_2)
    // & 0xFF to "transform" in an unsigned byte
    int payloadLength = (metadata[2] & 0xFF) + 256 * (metadata[3] & 0xFF);

    // read the payload
    byte[] payload = input.readNBytes(payloadLength);

    /**
     * The payload may exceed (2^8)^2 - 1 bytes, the maximum supported by the protocol. In this
     * case, the payload is split into multiple messages. The first message contains the full length
     * of the original message in the payload as an int. The following messages contain the payload
     * of the original message split into chunks of at most 256 * 257 bytes.
     */
    if (code.equals(MessageCode.SPLIT)) {
      ProtocolMessage leader = new ProtocolMessage(protocolVersion, code, payload, payloadLength);
      int fullLength = (int) leader.getPayloadAsObject();

      int readLength = 0;
      byte[] readPayload = new byte[0];

      ProtocolMessage next = null;

      do {
        // get the next message
        next = ProtocolMessage.fromDataStream(input);

        // update the read length
        readLength += next.getPayloadLength();

        byte[] nextPayload = next.getPayload();

        // concat the next payload to the read payload
        byte[] newReadPayload = new byte[readPayload.length + nextPayload.length];

        System.arraycopy(readPayload, 0, newReadPayload, 0, readPayload.length);
        System.arraycopy(nextPayload, 0, newReadPayload, readPayload.length, nextPayload.length);

        readPayload = newReadPayload;
      } while (readLength < fullLength);

      return new ProtocolMessage(protocolVersion, next.getCode(), readPayload, readLength);
    }

    return new ProtocolMessage(protocolVersion, code, payload, payloadLength);
  }

  public byte[] toByteStream() throws IOException {
    /**
     * The payload may exceed (2^8)^2 - 1 bytes, the maximum supported by the protocol. In this
     * case, we will need to split the payload into multiple messages.
     */
    if (this.payloadLength > MAX_PAYLOAD_LENGTH) {
      // inform the counterpart the message is split, sending the total length
      ProtocolMessage leader = new ProtocolMessage(MessageCode.SPLIT, this.payloadLength);

      int completed = 0;
      byte[] result = leader.toByteStream();

      // split the payload into chunks and build a message for each chunk
      do {
        int nextPayloadLength = this.payloadLength - completed;

        if (nextPayloadLength > MAX_PAYLOAD_LENGTH)
          nextPayloadLength = MAX_PAYLOAD_LENGTH;

        // get the chunk of the payload
        byte[] nextPayload = new byte[nextPayloadLength];
        System.arraycopy(this.payload, completed, nextPayload, 0, nextPayloadLength);

        // create a message with this chunk of the payload
        ProtocolMessage next = new ProtocolMessage(code, nextPayload);

        // concatenate the message to the result
        int messageLength = nextPayloadLength + METADATA_LENGTH;
        byte[] newResult = new byte[result.length + messageLength];

        System.arraycopy(result, 0, newResult, 0, result.length);
        System.arraycopy(next.toByteStream(), 0, newResult, result.length, messageLength);

        result = newResult;

        // update completed
        completed += nextPayloadLength;
      } while (completed < this.payloadLength);

      return result;
    }

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

  public String getStringifiedPayload() {
    return new String(this.payload);
  }

  public Object getPayloadAsObject() throws IOException, ClassNotFoundException {
    ByteArrayInputStream bis = new ByteArrayInputStream(this.payload);
    ObjectInputStream in = new ObjectInputStream(bis);
    Object o = in.readObject();
    in.close();
    return o;
  }

  public JsonObject getPayloadAsJson() {
    JsonReader reader = Json.createReader(new StringReader(getStringifiedPayload()));
    JsonObject jsonObject = reader.readObject();
    reader.close();
    return jsonObject;
  }

  public String toString() {
    return String.format(" --- ProtocolMessage version %d ---\n%s request, payload length: %d%s",
        this.protocolVersion, this.code.toString(), this.payloadLength,
        this.payloadLength != 0 ? "\nPayload: " + new String(this.payload) : "");
  }
}
