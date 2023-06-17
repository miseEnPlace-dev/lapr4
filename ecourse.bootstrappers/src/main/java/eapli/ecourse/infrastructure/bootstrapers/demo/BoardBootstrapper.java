package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.boardmanagement.application.CreateBoardController;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.ChangePostItController;
import eapli.ecourse.postitmanagement.application.CreatePostItController;
import eapli.ecourse.postitmanagement.application.ImageEncoderService;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class BoardBootstrapper extends UsersBootstrapperBase implements Action {
  private static Logger logger = LogManager.getLogger(BoardBootstrapper.class);

  private final String IMAGE_PATH = "_p1.png";

  private CreateBoardController ctrl =
      new CreateBoardController(PersistenceContext.repositories().boards(),
          AuthzRegistry.userService(), AuthzRegistry.authorizationService());

  private CreatePostItController ctrlPostIt =
      new CreatePostItController(PersistenceContext.repositories().boards(),
          PersistenceContext.repositories().postIts(), new ImageEncoderService());

  private TransactionalContext ctx = PersistenceContext.repositories().newTransactionalContext();

  private Map<SystemUser, PermissionType> getPermissions() {
    SystemUser u1 = registerTeacher("user3", "Password1", "firstName", "lastName", "email@ddd.com",
        "abc", "12345678", "01/01/2000");
    SystemUser u2 = registerTeacher("user4", "Password1", "firstName", "lastName", "email@ddd.com",
        "cbo", "12345679", "01/01/2000");

    Map<SystemUser, PermissionType> map = new HashMap<>();
    map.put(u1, new PermissionType(PermissionType.Type.READ));
    map.put(u2, new PermissionType(PermissionType.Type.WRITE));

    return map;
  }

  private Map<String, Integer> getColumns() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("To Do", 1);
    map.put("Doing", 2);
    map.put("Done", 3);

    return map;
  }

  private Map<String, Integer> getRows() {
    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("High", 1);
    map.put("Medium", 2);
    map.put("Low", 3);
    map.put("Very Low", 4);

    return map;
  }

  @Override
  public boolean execute() {
    Board b = ctrl.createBoard("example", getPermissions(), getColumns(), getRows());

    String path = null;

    ClassLoader classLoader = getClass().getClassLoader();
    InputStream resource = classLoader.getResourceAsStream(IMAGE_PATH);

    byte[] image = null;

    UserManagementService userSvc = AuthzRegistry.userService();
    SystemUser u = userSvc.userOfIdentity(Username.valueOf("poweruser")).orElseThrow();

    try {
      if (resource != null) {
        // read the file and convert it to base64
        image = IOUtils.toByteArray(resource);
      } else
        logger.warn("Image {} not found", IMAGE_PATH);

      ctrlPostIt.createPostIt(b.identity(), 1, 2, "PostIt1", null, image, u);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      ctrlPostIt.createPostIt(b.identity(), 2, 3, "PostIt4",
          "Some very big descriptive description!", path, u);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      PostIt p2 = ctrlPostIt.createPostIt(b.identity(), 2, 2, "PostIt2", "Description", image, u);

      ChangePostItController c = new ChangePostItController(ctx,
          PersistenceContext.repositories().boards(), PersistenceContext.repositories().postIts());

      c.changePostIt(p2.identity(), "PostIt2.1", 2, 2, "Description updated", null);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return true;
  }
}
