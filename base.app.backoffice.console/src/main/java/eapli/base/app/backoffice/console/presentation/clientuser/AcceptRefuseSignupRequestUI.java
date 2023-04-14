/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation.clientuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eapli.base.clientusermanagement.application.AcceptRefuseSignupFactory;
import eapli.base.clientusermanagement.application.AcceptRefuseSignupRequestController;
import eapli.base.clientusermanagement.domain.SignupRequest;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 *
 * Created by AJS on 08/04/2016.
 */
@SuppressWarnings("squid:S106")
public class AcceptRefuseSignupRequestUI extends AbstractUI {
  private static final Logger LOGGER = LoggerFactory.getLogger(AcceptRefuseSignupRequestUI.class);

  private final AcceptRefuseSignupRequestController theController = AcceptRefuseSignupFactory
      .build();

  @Override
  protected boolean doShow() {
    final SelectWidget<SignupRequest> selector = new SelectWidget<>("Pending signups",
        this.theController.listPendingSignupRequests(), new SignupRequestPrinter());
    selector.show();
    final SignupRequest theSignupRequest = selector.selectedElement();
    if (theSignupRequest != null) {
      System.out.println("1. Accept Signup Request");
      System.out.println("2. Refuse Signup Request");
      System.out.println("0. Exit");

      final int option = Console.readOption(1, 2, 0);
      try {
        switch (option) {
          case 1:
            this.theController.acceptSignupRequest(theSignupRequest);
            break;
          case 2:
            this.theController.refuseSignupRequest(theSignupRequest);
            break;
          default:
            System.out.println("No valid option selected");
            break;
        }
      } catch (IntegrityViolationException | ConcurrencyException ex) {
        LOGGER.error("Error performing the operation", ex);
        System.out.println(
            "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
      }
    }
    return false;
  }

  @Override
  public String headline() {
    return "Accept of Refuse Signup Requests";
  }
}
