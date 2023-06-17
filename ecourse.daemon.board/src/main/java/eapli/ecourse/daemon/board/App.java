package eapli.ecourse.daemon.board;

import eapli.ecourse.AppSettings;
import eapli.ecourse.common.board.TcpServer;
import eapli.ecourse.infrastructure.auth.PasswordEncoderContext;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * Shared Board Server.
 *
 * This application holds a TCP server used to communicate with the Shared Board
 * App by using the defined protocol.
 */
public class App {
  public static void main(String[] args) {
    AuthzRegistry.configure(PersistenceContext.repositories().users(), new ClientPasswordPolicy(),
        PasswordEncoderContext.passwordHash());

    AppSettings appSettings = new AppSettings();

    boolean isSecure = appSettings.isSSLEnabled();
    Integer port = appSettings.boardServerPort();

    TcpServer server = new TcpServer(port, ClientHandler.class, isSecure);
    server.run();
  }
}
