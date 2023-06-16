package eapli.ecourse.app.board.controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.common.board.http.Request;
import eapli.ecourse.common.board.http.Response;
import eapli.ecourse.common.board.http.RouteController;

public class WebSocketController implements RouteController {
  private static Logger logger = LogManager.getLogger(GetOnlineCountController.class);

  public WebSocketController() {
  }

  @Override
  public void handle(Request req, Response res) {
    // Reference:
    // https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API/Writing_a_WebSocket_server_in_Java

    DataOutputStream output = res.getOutput();

    // handshaking: When a client connects to a server, it sends a GET request to
    // upgrade the
    // connection to a WebSocket from a simple HTTP request.

    // 1. Obtain the value of Sec-WebSocket-Key request header without any leading
    // and trailing
    // whitespace

    String wsKey = findWSKeyHeader(req);

    if (wsKey == null) {
      res.status(400).send("Bad Request");
      return;
    }

    // 2. Link it with "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"
    // 3. Compute SHA-1 and Base64 code of it

    try {
      String code = Base64.getEncoder()
          .encodeToString(MessageDigest.getInstance("SHA-1")
              .digest((wsKey + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("UTF-8")))
          + "\r\n\r\n";

      String headers = "HTTP/1.1 101 Switching Protocols\r\n" + "Connection: Upgrade\r\n"
          + "Upgrade: websocket\r\n" + "Sec-WebSocket-Accept: " + code;

      byte[] response = headers.getBytes("UTF-8");

      // 4. Write it back as value of Sec-WebSocket-Accept response header as part of
      // an HTTP
      // response.
      output.write(response, 0, response.length);

      // decoding: After a successful handshake, client can send messages to the
      // server, but now these are encoded.

      byte[] decoded = new byte[6];
      byte[] encoded = new byte[] { (byte) 198, (byte) 131, (byte) 130, (byte) 182, (byte) 194, (byte) 135 };
      byte[] key = new byte[] { (byte) 167, (byte) 225, (byte) 225, (byte) 210 };

      for (int i = 0; i < encoded.length; i++) {
        decoded[i] = (byte) (encoded[i] ^ key[i & 0x3]);
      }

      System.out.println(new String(decoded, "UTF-8"));

    } catch (IOException | NoSuchAlgorithmException e) {
      logger.error("Error sending response", e);
    }
  }

  private String findWSKeyHeader(Request req) {
    for (String header : req.getHeaders()) {
      Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(header);
      if (match.find())
        return match.group(1);
    }
    return null;
  }
}
