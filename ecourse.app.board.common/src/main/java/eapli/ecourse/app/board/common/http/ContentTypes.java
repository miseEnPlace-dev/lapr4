package eapli.ecourse.app.board.common.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ContentTypes {
  public static final String HTML = "text/html; charset=UTF-8";
  public static final String CSS = "text/css";
  public static final String JS = "application/javascript";
  public static final String JSON = "application/json";
  public static final String PNG = "image/png";
  public static final String JPG = "image/jpg";
  public static final String JPEG = "image/jpeg";
  public static final String GIF = "image/gif";
  public static final String SVG = "image/svg+xml";
  public static final String ICO = "image/x-icon";
  public static final String PDF = "application/pdf";
  public static final String ZIP = "application/zip";
  public static final String GZIP = "application/gzip";
  public static final String TAR = "application/x-tar";
  public static final String BZ = "application/x-bzip";
  public static final String BZ2 = "application/x-bzip2";
  public static final String TXT = "text/plain";
  public static final String XML = "text/xml";
  public static final String XLS = "application/vnd.ms-excel";
  public static final String XLSX =
      "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  public static final String DOC = "application/msword";
  public static final String DOCX =
      "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
  public static final String PPT = "application/vnd.ms-powerpoint";
  public static final String PPTX =
      "application/vnd.openxmlformats-officedocument.presentationml.presentation";
  public static final String MP3 = "audio/mpeg";
  public static final String MP4 = "video/mp4";
  public static final String OGG = "audio/ogg";
  public static final String WAV = "audio/wav";
  public static final String WEBM = "video/webm";
  public static final String WMA = "audio/x-ms-wma";
  public static final String WMV = "video/x-ms-wmv";
  public static final String WOFF = "font/woff";
  public static final String WOFF2 = "font/woff2";
  public static final String TTF = "font/ttf";
  public static final String OTF = "font/otf";
  public static final String EOT = "application/vnd.ms-fontobject";
  public static final String SFNT = "application/font-sfnt";
  public static final String RSS = "application/rss+xml";
  public static final String FORM_DATA = "multipart/form-data";

  /**
   * Get the corresponding content type from a file.
   */
  public static String getContentTypeFromFile(File file) {
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
