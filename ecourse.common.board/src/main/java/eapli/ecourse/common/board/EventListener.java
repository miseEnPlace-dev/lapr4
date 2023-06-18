package eapli.ecourse.common.board;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class EventListener {
  // private static Logger logger = LogManager.getLogger(EventListener.class.getName());

  private Map<String, List<Socket>> subscriptions;

  public EventListener() {
    this.subscriptions = new HashMap<>();
  }

  public void subscribe(String id, Socket client) {
    List<Socket> clients = this.subscriptions.get(id);

    if (clients == null)
      clients = new ArrayList<>();

    clients.add(client);
    this.subscriptions.put(id, clients);
  }

  public void unsubscribe(String id, Socket client) {
    List<Socket> clients = this.subscriptions.get(id);

    if (clients != null) {
      clients.remove(client);

      if (clients.isEmpty()) {
        this.subscriptions.remove(id);
      }
    }
  }

  public void publish(String id, ProtocolMessage message) {
    List<Socket> clients = this.subscriptions.get(id);

    if (clients != null) {
      for (Socket client : clients) {
        try {
          DataOutputStream out = new DataOutputStream(client.getOutputStream());
          out.write(message.toByteStream());
        } catch (IOException e) {
          // nao da para enviar temos pena
          unsubscribe(id, client);
          // logger.error("Failed to publish message to client", e);
        }
      }
    }
  }
}
