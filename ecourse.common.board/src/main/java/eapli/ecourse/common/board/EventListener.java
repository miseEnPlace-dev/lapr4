package eapli.ecourse.common.board;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class EventListener {
  // private static Logger logger =
  // LogManager.getLogger(EventListener.class.getName());

  private Map<String, List<Socket>> subscriptions;
  private Map<String, List<Socket>> clientsSockets;

  public EventListener() {
    this.subscriptions = new HashMap<>();
    this.clientsSockets = new HashMap<>();
  }

  public void subscribe(Socket client, String id) {
    List<Socket> clients = this.subscriptions.get(id);

    if (clients == null) {
      clients = new ArrayList<>();
      subscriptions.put(id, clients);
    }

    clients.add(client);
  }

  public void subscribe(Socket client, String... ids) {
    for (String id : ids) {
      subscribe(client, id);
    }
  }

  public void subscribe(Socket client, Iterable<String> ids) {
    for (String id : ids) {
      subscribe(client, id);
    }
  }

  public void unsubscribe(Socket client, String id) {
    List<Socket> clients = this.subscriptions.get(id);

    if (clients != null) {
      Iterator<Socket> iterator = clients.iterator();
      while (iterator.hasNext()) {
        Socket socket = iterator.next();
        if (socket.equals(client)) {
          iterator.remove();
        }
      }
    }
  }

  public void unsubscribe(Socket client) {
    for (String id : this.subscriptions.keySet()) {
      unsubscribe(client, id);
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
          // logger.error("Failed to publish message to client", e);
        }
      }
    }
  }

  public void subscribeUser(String username, String id) {
    List<Socket> sockets = this.clientsSockets.get(username);

    if (sockets != null) {
      for (Socket s : sockets) {
        subscribe(s, id);
      }
    }
  }

  public void addClient(Socket socket, String username) {
    List<Socket> list = clientsSockets.get(username);

    if (list == null) {
      list = new ArrayList<>();
      clientsSockets.put(username, list);
    }

    list.add(socket);
    subscribe(socket, "all", username);
  }

  public void removeClient(Socket socket) {
    unsubscribe(socket);

    for (String username : clientsSockets.keySet()) {
      List<Socket> list = clientsSockets.get(username);

      if (list != null)
        list.remove(socket);
    }
  }
}
