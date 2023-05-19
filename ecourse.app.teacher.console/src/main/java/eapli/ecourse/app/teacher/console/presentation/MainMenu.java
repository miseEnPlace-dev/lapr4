package eapli.ecourse.app.teacher.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.common.console.presentation.authz.MyUserMenu;
import eapli.ecourse.app.common.console.presentation.board.CreateBoardUI;
import eapli.ecourse.app.teacher.console.presentation.exams.CreateExamUI;
import eapli.ecourse.app.teacher.console.presentation.exams.ListCourseExamsUI;
import eapli.ecourse.app.teacher.console.presentation.questions.AddQuestionsUI;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class MainMenu extends AbstractUI {

  private static final String SEPARATOR_LABEL = "--------------";

  private static final int EXIT_OPTION = 0;

  private static final int ADD_QUESTIONS_OPTION = 1;
  private static final int CREATE_EXAM_OPTION = 2;
  private static final int LIST_COURSE_EXAMS = 3;
  private static final int CREATE_BOARD = 4;
  private static final int MY_USER_OPTION = 5;

  private final AuthorizationService authz = AuthzRegistry.authorizationService();

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
    return authz.session().map(s -> "Client [ @" + s.authenticatedUser().identity() + " ]")
        .orElse("Client [ ==Anonymous== ]");
  }

  private Menu buildMainMenu() {
    final Menu mainMenu = new Menu();

    final Menu myUserMenu = new MyUserMenu(ClientRoles.TEACHER);
    mainMenu.addItem(ADD_QUESTIONS_OPTION, "Add Questions", new AddQuestionsUI()::show);
    mainMenu.addItem(CREATE_EXAM_OPTION, "Create Exam", new CreateExamUI()::show);
    mainMenu.addItem(LIST_COURSE_EXAMS, "List Course Exams", new ListCourseExamsUI()::show);
    mainMenu.addItem(CREATE_BOARD, "Create Board", new CreateBoardUI()::show);
    mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

    if (!Application.settings().isMenuLayoutHorizontal()) {
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
    }

    if (!Application.settings().isMenuLayoutHorizontal()) {
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
    }

    mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

    return mainMenu;
  }
}
