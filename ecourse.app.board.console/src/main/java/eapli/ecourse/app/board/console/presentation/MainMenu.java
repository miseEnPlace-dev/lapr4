package eapli.ecourse.app.board.console.presentation;

import java.util.Optional;

import eapli.ecourse.Application;
import eapli.ecourse.app.board.console.presentation.mainmenu.ArchiveBoardUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.ChangePostItUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.CommTestUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.CreatePostItUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.DeletePostItUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.LogoutUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.SessionInfoUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.ShareBoardUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.UndoPostItChangeUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.ViewBoardHistoryUI;
import eapli.ecourse.app.board.console.presentation.mainmenu.ViewPostItHistoryUI;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class MainMenu extends AbstractUI {

  private static final String SEPARATOR_LABEL = "--------------";

  private static final int EXIT_OPTION = 0;

  private static final int SHARE_BOARD_OPTION = 1;
  private static final int CREATE_POST_IT_OPTION = 2;
  private static final int CHANGE_POST_IT_OPTION = 3;
  private static final int DELETE_POSTITOPTION = 4;
  private static final int UNDO_POST_IT_CHANGE_OPTION = 5;
  private static final int VIEW_BOARD_HISTORY_OPTION = 6;
  private static final int ARCHIVE_BOARD_OPTION = 7;

  private static final int COMMTEST_OPTION = 8;
  private static final int SESSION_INFO_OPTION = 9;

  private final Menu menu;
  private final MenuRenderer renderer;

  public MainMenu() {
    menu = buildMainMenu();
    renderer = getRenderer(menu);
  }

  private MenuRenderer getRenderer(final Menu menu) {
    final MenuRenderer theRenderer;
    if (Application.settings().isMenuLayoutHorizontal()) {
      theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    } else {
      theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    }
    return theRenderer;
  }

  @Override
  public boolean doShow() {
    return renderer.render();
  }

  @Override
  public boolean show() {
    drawFormTitle();
    return doShow();
  }

  @Override
  public String headline() {
    Optional<UserDTO> user = BoardBackend.getInstance().getCredentialStore().getUser();

    if (!user.isPresent())
      return "Shared Board App [ ==Anonymous== ]";

    return "Shared Board App [ @" + user.get().getUsername() + " ]";
  }

  private Menu buildMainMenu() {
    final Menu mainMenu = new Menu();

    mainMenu.addItem(SHARE_BOARD_OPTION, "Share Board", new ShareBoardUI()::show);
    mainMenu.addItem(CREATE_POST_IT_OPTION, "Create Post-It", new CreatePostItUI()::show);
    mainMenu.addItem(CHANGE_POST_IT_OPTION, "Change Post-It", new ChangePostItUI()::show);
    mainMenu.addItem(DELETE_POSTITOPTION, "Delete Post-It", new DeletePostItUI()::show);
    mainMenu.addItem(UNDO_POST_IT_CHANGE_OPTION, "Undo Last Post-It Change",
        new UndoPostItChangeUI()::show);
    mainMenu.addSubMenu(VIEW_BOARD_HISTORY_OPTION, buildBoardHistoryMenu());
    mainMenu.addItem(ARCHIVE_BOARD_OPTION, "Toggle board archived state", new ArchiveBoardUI()::show);

    mainMenu.addItem(COMMTEST_OPTION, "Send COMMTEST", new CommTestUI()::show);
    mainMenu.addItem(SESSION_INFO_OPTION, "Session Info", new SessionInfoUI()::show);

    if (!Application.settings().isMenuLayoutHorizontal()) {
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
    }

    mainMenu.addItem(EXIT_OPTION, "Logout", new LogoutUI()::show);

    return mainMenu;
  }

  private Menu buildBoardHistoryMenu() {
    final Menu boardHistoryMenu = new Menu("Board History >");

    boardHistoryMenu.addItem(1, "View Board History", new ViewBoardHistoryUI()::show);
    boardHistoryMenu.addItem(2, "View Post-It History", new ViewPostItHistoryUI()::show);
    boardHistoryMenu.addItem(EXIT_OPTION, "Return", () -> {
      return false;
    });

    return boardHistoryMenu;
  }
}
