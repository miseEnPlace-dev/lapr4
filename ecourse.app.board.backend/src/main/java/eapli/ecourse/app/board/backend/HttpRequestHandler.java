package eapli.ecourse.app.board.backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestHandler implements Runnable {
  private static final String HTTP_VERSION = "HTTP/1.1";

  private Socket client;

  public HttpRequestHandler(Socket socket) {
    this.client = socket;
  }

  private void sendHttpHeaders(DataOutputStream output, int contentLength) throws IOException {
    List<String> headers = new ArrayList<>();

    headers.add(HTTP_VERSION + " 200 OK");
    headers.add("Content-Type: text/html; charset=UTF-8");
    headers.add("Content-Length: " + contentLength);

    writeHeaders(output, headers);
  }

  private void sendHttpBody(DataOutputStream output, String content) throws IOException {
    output.writeBytes(content);
  }

  @Override
  public void run() {
    try {
      System.out.printf("[HTTP Request Handler Thread] Connected to %s port %d!\n",
          client.getInetAddress().toString(), client.getPort());

      // create a data input stream to read from the client
      DataInputStream input = new DataInputStream(client.getInputStream());

      // and a data output stream to write to the client
      DataOutputStream output = new DataOutputStream(client.getOutputStream());

      // read the headers
      List<String> headers = readHeaders(input);

      String content =
          "<html><head><title>Shared Board</title></head><body><h1>Shared Board</h1><p>Shared Board</p></body></html>";

      // send the headers
      sendHttpHeaders(output, content.length());

      // send the body
      sendHttpBody(output, content);

      client.close();

    } catch (IOException e) {
      System.out.println("[Client Handler Thread] Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Reads the headers from the input stream.
   *
   * @param input
   * @throws IOException
   */
  private static List<String> readHeaders(DataInputStream input) throws IOException {
    List<String> headers = new ArrayList<>();
    String line;

    do {
      line = "";

      // reading a header line has to be done byte by byte because we don't know the size of the
      // line beforehand
      byte[] buffer = new byte[1];

      do {
        input.read(buffer, 0, 1);

        if (buffer[0] == '\n' && buffer[0] != '\r')
          line.concat(new String(buffer));
      } while (buffer[0] != '\n');

      headers.add(line);
    } while (line.length() > 0);

    return headers;
  }

  /**
   * Writes the headers to the output stream.
   *
   * @param output
   * @param headers
   */
  private static void writeHeaders(DataOutputStream output, List<String> headers)
      throws IOException {
    for (String header : headers) {
      output.writeBytes(header);
      output.writeBytes("\r\n");
    }

    output.writeBytes("\r\n");
  }
}
