package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.daemon.board.clientstate.CredentialStore;

public class CommTestMessage extends Message {
  public CommTestMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket) {
    super(protocolMessage, output, socket);
  }

  @Override
  public void handle() throws IOException {
    CredentialStore credentialStore = ClientState.getInstance().getCredentialStore();

    if (credentialStore.isAuthenticated()) {
      send(new ProtocolMessage(MessageCode.ACK, credentialStore.getUsername()));
    } else {
      send(new ProtocolMessage(MessageCode.ERR, "Not Authenticated"));
    }
  }
}
