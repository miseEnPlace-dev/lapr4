package eapli.ecourse.app.board.backend.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Response {
  private static final String HTTP_VERSION = "HTTP/1.1";

  private int statusCode;
  private DataOutputStream output;

  public Response(DataOutputStream output) {
    this.statusCode = 200;
    this.output = output;
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

  // public void json() {}

  // public

  private void sendHttpResponse(byte[] content, int contentLength) throws IOException {
    sendHttpResponseHeaders(contentLength);
    output.write(content, 0, contentLength);
  }

  private void sendHttpResponseHeaders(int contentLength) throws IOException {
    List<String> headers = new ArrayList<>();

    headers.add(HTTP_VERSION + " " + this.statusCode);
    headers.add("Content-Type: text/html; charset=utf-8");
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
