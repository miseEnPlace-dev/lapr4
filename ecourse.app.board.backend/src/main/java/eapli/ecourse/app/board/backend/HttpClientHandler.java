package eapli.ecourse.app.board.backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.backend.http.Request;
import eapli.ecourse.app.board.backend.http.Response;
import eapli.ecourse.app.board.backend.http.Router;

public class HttpClientHandler implements Runnable {
  private Socket client;

  public HttpClientHandler(Socket socket) {
    this.client = socket;
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

      // create the router
      Router router = new Router();

      router.get("/", (req, res) -> {
        res.send("Hello World!");
        {
          // File file = new File(WWW_PATH + path);

          // // index.html if path is a directory
          // if (file.isDirectory())
          // file = new File(WWW_PATH + path + "/index.html");

          // // 404 if file does not exist
          // if (!file.exists()) {
          // String response = "Not Found";
          // sendHttpResponse(output, 404, "text/html; charset=UTF-8",
          // response.getBytes(StandardCharsets.UTF_8), response.length());
          // output.close();
          // input.close();
          // return;
          // }

          // String contentType = getContentTypeFromFile(file);

          // // send the headers and content
          // sendHttpResponseHeaders(output, 200, contentType, file.length());

          // // read from file
          // FileInputStream fileInputStream = new FileInputStream(file);

          // byte[] buffer = new byte[4096];
          // int bytes = 0;

          // // send the file piece by piece
          // while ((bytes = fileInputStream.read(buffer)) != -1) {
          // output.write(buffer, 0, bytes);
          // output.flush();
          // }

          // fileInputStream.close();
        }
      });

      router.get("/hello", (req, res) -> {
        res.send("Hello World from route /hello!");
      });

      router.get("/test", (req, res) -> {
        res.status(404).send("Test route");
      });

      // create the request and response objects
      Request req = new Request(method, path, address, headers);
      Response res = new Response(output);

      router.handleRoute(req, res);

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

  /**
   * Get the corresponding content type from a file.
   */
  private static String getContentTypeFromFile(File file) {
    Map<String, String> fileContentTypes = new HashMap<String, String>();

    fileContentTypes.put("pdf", "application/pdf");
    fileContentTypes.put("js", "application/javascript");
    fileContentTypes.put("txt", "text/plain");
    fileContentTypes.put("gif", "image/gif");
    fileContentTypes.put("png", "image/png");
    fileContentTypes.put("jpeg", "image/jpeg");
    fileContentTypes.put("ico", "image/x-icon");
    fileContentTypes.put("html", "text/html; charset=UTF-8");
    fileContentTypes.put("css", "text/css");
    fileContentTypes.put("xml", "text/xml");
    fileContentTypes.put("json", "application/json");

    String[] tmp = file.getName().split("[.]");
    String extension = tmp[tmp.length - 1];

    String result = fileContentTypes.get(extension);

    if (result == null)
      result = "application/x-binary";

    return result;
  }
}
