package eapli.ecourse.app.board.common.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

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

    String contentType = ContentTypes.getContentTypeFromFile(file);

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
}
