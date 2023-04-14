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
package eapli.base.app.user.console;

import eapli.base.app.common.console.BaseApplication;
import eapli.base.app.user.console.presentation.FrontMenu;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

/**
 * Base User App.
 */
@SuppressWarnings("squid:S106")
public final class BaseUserApp extends BaseApplication {

	/**
	 * Empty constructor is private to avoid instantiation of this class.
	 */
	private BaseUserApp() {
	}

	public static void main(final String[] args) {

		AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(),
				new PlainTextEncoder());

		new BaseUserApp().run(args);
	}

	@Override
	protected void doMain(String[] args) {
		new FrontMenu().show();

	}

	@Override
	protected String appTitle() {
		return "Base User App";
	}

	@Override
	protected String appGoodbye() {
		return "Bye, bye";
	}

	@Override
	protected void doSetupEventHandlers(EventDispatcher dispatcher) {
		// NOP
	}
}
