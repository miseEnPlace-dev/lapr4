package eapli.ecourse.app.board.common.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Response {
  private static final String HTTP_VERSION = "HTTP/1.1";

  private int statusCode;
  private String contentType;
  private DataOutputStream output;

  public Response(DataOutputStream output) {
    this.statusCode = 200;
    this.output = output;
    this.contentType = "text/html; charset=utf-8";
  }

  public Response status(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public void send(byte[] body) {
    try {
      sendHttpResponse(body, body.length);
    } catch (IOException e) {
      Logger logger = LogManager.getLogger(Response.class);
      logger.error("Error sending response", e);
    }
  }

  public void send(String message) {
    try {
      byte[] body = message.getBytes(StandardCharsets.UTF_8);
      sendHttpResponse(body, body.length);
    } catch (IOException e) {
      Logger logger = LogManager.getLogger(Response.class);
      logger.error("Error sending response", e);
    }
  }

  public void sendByteBuffer(ByteBuffer buffer) {
    try {
      sendHttpResponseHeaders(buffer.capacity());

      WritableByteChannel channel = Channels.newChannel(output);
      channel.write(buffer);
    } catch (IOException e) {
      Logger logger = LogManager.getLogger(Response.class);
      logger.error("Error sending response", e);
    }
  }

  public void json(JsonObject jsonObject) {
    setContentType(ContentTypes.JSON);

    try {
      byte[] body = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
      sendHttpResponse(body, body.length);
    } catch (IOException e) {
      Logger logger = LogManager.getLogger(Response.class);
      logger.error("Error sending response", e);
    }
  }

  // public

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  private void sendHttpResponse(byte[] content, int contentLength) throws IOException {
    sendHttpResponseHeaders(contentLength);
    output.write(content, 0, contentLength);
  }

  private void sendHttpResponseHeaders(int contentLength) throws IOException {
    List<String> headers = new ArrayList<>();

    headers.add(HTTP_VERSION + " " + this.statusCode);
    headers.add("Content-Type: " + contentType);
    headers.add("Content-Length: " + contentLength);

    writeHeaders(headers);
  }

  /**
   * Writes the headers to the output stream.
   *
   * @param output
   * @param headers
   */
  private void writeHeaders(List<String> headers) throws IOException {
    for (String header : headers) {
      output.writeBytes(header);
      output.writeBytes("\r\n");
    }

    output.writeBytes("\r\n");
  }
}
