package eapli.ecourse.app.board.backend.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

public class StaticMiddleware implements Middleware {
  private String path;

  public StaticMiddleware(String path) {
    this.path = path;
  }

  @Override
  public void handle(Request req, Response res, NextFunction next) {
    File file = new File(this.path + req.getPath().getPath());

    // index.html if path is a directory
    if (file.isDirectory())
      file = new File(file.getPath() + "/index.html");

    String contentType = getContentTypeFromFile(file);

    try {
      // read from file
      FileInputStream fileInputStream = new FileInputStream(file);
      FileChannel fileChannel = fileInputStream.getChannel();

      MappedByteBuffer buffer =
          fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

      res.setContentType(contentType);
      res.sendByteBuffer(buffer);

      fileChannel.close();
      fileInputStream.close();
    } catch (IOException e) {
      next.next();
    }
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
