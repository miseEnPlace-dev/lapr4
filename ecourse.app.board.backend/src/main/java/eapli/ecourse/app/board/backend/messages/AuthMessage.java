package eapli.ecourse.app.board.backend.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import eapli.ecourse.app.board.common.protocol.MessageCode;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;
import eapli.ecourse.infrastructure.authz.AuthenticationCredentialHandler;
import eapli.ecourse.infrastructure.authz.CredentialHandler;

public class AuthMessage extends Message {
  public AuthMessage(ProtocolMessage protocolMessage, DataOutputStream output) {
    super(protocolMessage, output);
  }

  @Override
  public void handle() throws IOException {
    CredentialHandler credentialHandler = new AuthenticationCredentialHandler();

    String payload = new String(protocolMessage.getPayload());

    String fields[] = payload.split("\0");

    if (fields.length < 2) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    String username = fields[0], password = fields[1];

    // not logged in
    if (!credentialHandler.authenticated(username, password, null)) {
      send(new ProtocolMessage(MessageCode.ERR, "Wrong credentials!"));
      return;
    }

    send(new ProtocolMessage(MessageCode.ACK, username));
  }
}
