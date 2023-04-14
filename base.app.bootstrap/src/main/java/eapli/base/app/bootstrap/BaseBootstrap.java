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
package eapli.base.app.bootstrap;

import eapli.base.app.common.console.BaseApplication;
import eapli.base.clientusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.base.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.base.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.BaseDemoBootstrapper;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.smoketests.BaseDemoSmokeTester;
import eapli.base.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.util.ArrayPredicates;

/**
 * Base Bootstrapping data app
 *
 */
@SuppressWarnings("squid:S106")
public final class BaseBootstrap extends BaseApplication {
  /**
   * avoid instantiation of this class.
   */
  private BaseBootstrap() {
  }

  private boolean isToBootstrapDemoData;
  private boolean isToRunSampleE2E;

  public static void main(final String[] args) {

    AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(),
        new PlainTextEncoder());

    new BaseBootstrap().run(args);
  }

  @Override
  protected void doMain(final String[] args) {
    handleArgs(args);

    System.out.println("\n\n------- MASTER DATA -------");
    new BaseBootstrapper().execute();

    if (isToBootstrapDemoData) {
      System.out.println("\n\n------- DEMO DATA -------");
      new BaseDemoBootstrapper().execute();
    }
    if (isToRunSampleE2E) {
      System.out.println("\n\n------- BASIC SCENARIO -------");
      new BaseDemoSmokeTester().execute();
    }
  }

  private void handleArgs(final String[] args) {
    isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:basic");
    if (isToRunSampleE2E) {
      isToBootstrapDemoData = true;
    } else {
      isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
    }
  }

  @Override
  protected String appTitle() {
    return "Bootstrapping Base data ";
  }

  @Override
  protected String appGoodbye() {
    return "Bootstrap data done.";
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
    dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
    dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
  }
}
