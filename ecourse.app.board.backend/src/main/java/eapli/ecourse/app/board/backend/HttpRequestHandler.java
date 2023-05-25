package eapli.ecourse.app.board.backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
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

  /**
   * Sends the HTTP response headers to the client.
   */
  private void sendHttpResponseHeaders(DataOutputStream output, int status, String contentType,
      int contentLength) throws IOException {
    List<String> headers = new ArrayList<>();

    headers.add(HTTP_VERSION + " " + status);
    headers.add("Content-Type: " + contentType);
    headers.add("Content-Length: " + contentLength);

    writeHeaders(output, headers);
  }

  /**
   * Sends the content to the client.
   *
   * @param output
   * @param content
   * @throws IOException
   */
  private void sendHttpResponse(DataOutputStream output, int status, String contentType,
      String content) throws IOException {
    sendHttpResponseHeaders(output, status, contentType, content.length());

    output.writeBytes(content);
  }

  @Override
  public void run() {
    try {
      // System.out.printf("[HTTP Request Handler Thread] Connected to %s port %d!\n",
      // client.getInetAddress().toString(), client.getPort());

      // create a data input stream to read from the client
      DataInputStream input = new DataInputStream(client.getInputStream());

      // and a data output stream to write to the client
      DataOutputStream output = new DataOutputStream(client.getOutputStream());

      // read the headers
      List<String> headers = readHeaders(input);

      System.out.println(headers.get(0));

      // process the request
      // ...
      String method = headers.get(0).split(" ")[0];
      String path = headers.get(0).split(" ")[1];

      if (!path.equals("/") || !method.equals("GET")) {
        sendHttpResponse(output, 404, "text/html; charset=UTF-8", "Not Found");
        output.close();
        input.close();
        return;
      }

      // read from file
      CustomScanner scanner = new CustomScanner("www/index.html");
      String content = new String("");

      while (scanner.hasNextLine())
        content += scanner.nextLine();

      // send the headers and content
      sendHttpResponse(output, 200, "text/html; charset=UTF-8", content);

      output.close();
      input.close();
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

        if (buffer[0] != '\n' && buffer[0] != '\r')
          line += new String(buffer);
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
