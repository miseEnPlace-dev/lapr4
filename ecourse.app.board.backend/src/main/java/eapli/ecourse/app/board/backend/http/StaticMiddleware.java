package eapli.ecourse.app.board.backend.http;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class StaticMiddleware implements Middleware {
  private String path;

  public StaticMiddleware(String path) {
    this.path = path;
  }

  @Override
  public void handle(Request req, Response res, NextFunction next) {
    File file = new File(this.path + req.getPath());

    // index.html if path is a directory
    if (file.isDirectory())
      file = new File(file.getPath() + "/index.html");

    // continue if file does not exist
    if (!file.exists()) {
      next.next();
      return;
    }

    String contentType = getContentTypeFromFile(file);

    // send the headers and content
    // sendHttpResponseHeaders(output, 200, contentType, file.length());

    // read from file
    // FileInputStream fileInputStream = new FileInputStream(file);

    byte[] buffer = new byte[4096];
    int bytes = 0;

    // send the file piece by piece
    // while ((bytes = fileInputStream.read(buffer)) != -1) {
    // output.write(buffer, 0, bytes);
    // output.flush();
    // }

    // fileInputStream.close();
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
