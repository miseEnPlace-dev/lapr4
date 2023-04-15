/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.ecafeteria.app.backoffice.console.presentation;

import eapli.cafeteria.app.common.console.presentation.authz.MyUserMenu;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser.AcceptRefuseSignupRequestAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishNutricionalInfoAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishPriceAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishTypePessimisticLockUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.RegisterDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.RegisterDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerCaloricCategoryAsTuplesUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerCaloricCategoryUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerDishTypeUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportHighCaloriesDishesUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto.ListDishViaDTOUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto.RegisterDishViaDTOUI;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.ListMaterialAction;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.RegisterMaterialAction;
import eapli.ecafeteria.app.backoffice.console.presentation.meals.ListMealUI;
import eapli.ecafeteria.app.backoffice.console.presentation.meals.RegisterMealUI;
import eapli.ecafeteria.usermanagement.domain.CafeteriaRoles;
import eapli.framework.actions.Actions;
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

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // DISH TYPES
    private static final int DISH_TYPE_REGISTER_OPTION = 1;
    private static final int DISH_TYPE_LIST_OPTION = 2;
    private static final int DISH_TYPE_CHANGE_OPTION = 3;
    private static final int DISH_TYPE_CHANGE_PESIMISTIC_LOCK_OPTION = 4;
    private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 5;

    // DISHES
    private static final int DISH_REGISTER_OPTION = 10;
    private static final int DISH_LIST_OPTION = 11;
    private static final int DISH_REGISTER_DTO_OPTION = 12;
    private static final int DISH_LIST_DTO_OPTION = 13;
    private static final int DISH_ACTIVATE_DEACTIVATE_OPTION = 14;
    private static final int DISH_CHANGE_OPTION = 15;

    // DISH PROPERTIES
    private static final int CHANGE_DISH_NUTRICIONAL_INFO_OPTION = 1;
    private static final int CHANGE_DISH_PRICE_OPTION = 2;

    // MATERIALS
    private static final int MATERIAL_REGISTER_OPTION = 1;
    private static final int MATERIAL_LIST_OPTION = 2;

    // REPORTING
    private static final int REPORTING_DISHES_PER_DISHTYPE_OPTION = 1;
    private static final int REPORTING_HIGH_CALORIES_DISHES_OPTION = 2;
    private static final int REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION = 3;

    // MEALS
    private static final int LIST_MEALS_OPTION = 1;
    private static final int MEAL_REGISTER_OPTION = 2;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 4;
    private static final int DISH_OPTION = 5;
    private static final int TRACEABILITY_OPTION = 6;
    private static final int MEALS_OPTION = 7;
    private static final int REPORTING_DISHES_OPTION = 8;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "eCAFETERIA [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("eCAFETERIA [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER,
                CafeteriaRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER,
                CafeteriaRoles.KITCHEN_MANAGER)) {
            final Menu kitchenMenu = buildKitchenMenu();
            mainMenu.addSubMenu(TRACEABILITY_OPTION, kitchenMenu);
        }
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER,
                CafeteriaRoles.MENU_MANAGER)) {
            final Menu dishTypeMenu = buildDishMenu();
            mainMenu.addSubMenu(DISH_OPTION, dishTypeMenu);
            final Menu menusMenu = buildMealsMenu();
            mainMenu.addSubMenu(MEALS_OPTION, menusMenu);
            // reporting
            final Menu reportingDishesMenu = buildReportingDishesMenu();
            mainMenu.addSubMenu(REPORTING_DISHES_OPTION, reportingDishesMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildDishMenu() {
        final Menu menu = new Menu("Dishes >");

        // dish types
        menu.addItem(DISH_TYPE_REGISTER_OPTION, "Register new Dish Type",
                new RegisterDishTypeAction());
        menu.addItem(DISH_TYPE_LIST_OPTION, "List all Dish Type", new ListDishTypeAction());
        menu.addItem(DISH_TYPE_CHANGE_OPTION, "Change Dish Type description",
                new ChangeDishTypeAction());
        menu.addItem(DISH_TYPE_CHANGE_PESIMISTIC_LOCK_OPTION, "Change Dish Type description (Pessimistic Lock)",
                new ChangeDishTypePessimisticLockUI()::show);
        menu.addItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish Type",
                new ActivateDeactivateDishTypeAction());

        // dishes
        menu.addItem(DISH_REGISTER_OPTION, "Register new Dish", new RegisterDishAction());
        menu.addItem(DISH_LIST_OPTION, "List all Dish", new ListDishAction());

        menu.addItem(DISH_REGISTER_DTO_OPTION, "Register new Dish (via DTO)",
                new RegisterDishViaDTOUI()::show);
        menu.addItem(DISH_LIST_DTO_OPTION, "List all Dish (via DTO)", new ListDishViaDTOUI()::show);

        menu.addItem(DISH_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish",
                new ActivateDeactivateDishAction());
        final Menu changeDishMenu = buildChangeDishMenu();
        menu.addSubMenu(DISH_CHANGE_OPTION, changeDishMenu);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildKitchenMenu() {
        final Menu menu = new Menu("Traceability >");

        menu.addItem(MATERIAL_REGISTER_OPTION, "Register new material",
                new RegisterMaterialAction());
        menu.addItem(MATERIAL_LIST_OPTION, "List all materials", new ListMaterialAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildChangeDishMenu() {
        final Menu menu = new Menu("Change Dish >");

        menu.addItem(CHANGE_DISH_NUTRICIONAL_INFO_OPTION, "Change Nutricional Info",
                new ChangeDishNutricionalInfoAction());
        menu.addItem(CHANGE_DISH_PRICE_OPTION, "Change Price", new ChangeDishPriceAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildReportingDishesMenu() {
        final Menu menu = new Menu("Reporting Dishes >");

        menu.addItem(REPORTING_DISHES_PER_DISHTYPE_OPTION, "Dishes per Dish Type",
                new ReportDishesPerDishTypeUI()::show);
        menu.addItem(REPORTING_HIGH_CALORIES_DISHES_OPTION, "High Calories Dishes",
                new ReportHighCaloriesDishesUI()::show);
        menu.addItem(REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION, "Dishes per Caloric Category",
                new ReportDishesPerCaloricCategoryUI()::show);
        menu.addItem(REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION + 1,
                "Dishes per Caloric Category (as tuples)",
                new ReportDishesPerCaloricCategoryAsTuplesUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildMealsMenu() {
        final Menu menusMenu = new Menu("Menus >");

        menusMenu.addItem(LIST_MEALS_OPTION, "List Meals (** DEBUG **)", new ListMealUI()::show);
        menusMenu.addItem(MEAL_REGISTER_OPTION, "Register meal on Menu",
                new RegisterMealUI()::show);

        menusMenu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menusMenu;
    }
}
