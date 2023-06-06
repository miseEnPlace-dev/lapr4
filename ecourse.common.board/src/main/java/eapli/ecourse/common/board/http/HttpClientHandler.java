package eapli.ecourse.common.board.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpClientHandler implements Runnable {
  private Socket client;
  private Router router;

  public HttpClientHandler(Socket client, Router router) {
    this.client = client;
    this.router = router;
  }

  @Override
  public void run() {
    try {
      // create a data input stream to read from the client
      DataInputStream input = new DataInputStream(client.getInputStream());

      // and a data output stream to write to the client
      DataOutputStream output = new DataOutputStream(client.getOutputStream());

      // read the headers
      List<String> headers = readHeaders(input);

      // process the request
      String method = headers.get(0).split(" ")[0];
      String path = headers.get(0).split(" ")[1];
      String address = client.getInetAddress().getHostAddress();

      Logger logger = LogManager.getLogger(HttpClientHandler.class);
      logger.debug(method + " " + path);

      // create the request and response objects
      Request req = new Request(method, path, address, headers);
      Response res = new Response(output);

      this.router.handle(req, res);

      output.close();
      input.close();
    } catch (IOException e) {
      System.out.println("[HTTP Client Handler Thread] Error: " + e.getMessage());
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

      // reading a header line has to be done byte by byte because we don't know the
      // size of the
      // line beforehand
      byte[] buffer = new byte[1];

      do {
        input.read(buffer, 0, 1);

        if (buffer[0] != '\n' && buffer[0] != '\r')
          line += new String(buffer);
      } while (buffer[0] != '\n');

      if (line.length() > 0)
        headers.add(line);
    } while (line.length() > 0);

    return headers;
  }
}
