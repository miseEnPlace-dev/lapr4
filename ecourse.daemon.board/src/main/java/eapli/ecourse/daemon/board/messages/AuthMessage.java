package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.EventListener;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.authz.AuthenticationCredentialHandler;
import eapli.ecourse.infrastructure.authz.CredentialHandler;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class AuthMessage extends Message {
  private BoardRepository boardRepository;

  public AuthMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter,
      EventListener eventListener) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter, eventListener);

    this.boardRepository = PersistenceContext.repositories().boards();

  }

  @Override
  public void handle() throws IOException {
    CredentialHandler credentialHandler = new AuthenticationCredentialHandler();
    ClientState clientState = ClientState.getInstance();
    UserManagementService userSvc = AuthzRegistry.userService();

    if (clientState.getCredentialStore().isAuthenticated()) {
      // ignore
      return;
    }

    String payload = new String(request.getPayload());

    String fields[] = payload.split("\0");

    if (fields.length < 2) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    String usernameStr = fields[0], password = fields[1];

    // not logged in
    if (!credentialHandler.authenticated(usernameStr, password, null)) {
      send(new ProtocolMessage(MessageCode.ERR, "Wrong credentials!"));
      return;
    }

    // get the system user
    Optional<SystemUser> optional = userSvc.userOfIdentity(Username.valueOf(usernameStr));

    if (!optional.isPresent()) {
      send(new ProtocolMessage(MessageCode.ERR, "Wrong credentials!"));
      return;
    }

    // user.toDTO() is not very good
    UserDTO user = UserDTO.from(optional.get());

    clientState.getCredentialStore().store(user);

    send(new ProtocolMessage(MessageCode.ACK, user));

    Username username = Username.valueOf(usernameStr);

    Iterable<Board> boards = boardRepository.findAllAccessibleByUser(username);
    Iterable<String> ids = StreamSupport.stream(boards.spliterator(), true).map(b -> b.identity().toString())
        .collect(Collectors.toUnmodifiableList());

    // subscribe to all, user & accessible boards
    eventListener.addClient(socket, usernameStr);
    eventListener.subscribe(socket, ids);
  }
}
