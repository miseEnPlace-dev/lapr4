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
package eapli.base.app.common.console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.base.Application;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;

/**
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public abstract class BaseApplication {

	// we are assuming we will always use the in process event
	// dispatcher. check the Spring version of the eCafeteria project
	// for an alternative
	final EventDispatcher dispatcher = InProcessPubSub.dispatcher();

	protected static final String SEPARATOR_HR = "=====================================";
	private static final Logger LOGGER = LogManager.getLogger(BaseApplication.class);

	/**
	 * @param args the command line arguments
	 */
	public void run(final String[] args) {
		printHeader();

		try {
			setupEventHandlers();

			doMain(args);

			printFooter();
		} catch (final Exception e) {
			System.out.println(
					"Something unexpected has happened and the application will terminate. Please check the logs.\n");
			LOGGER.error(e);
		} finally {
			clearEventHandlers();
		}

		// exiting the application, closing all threads
		System.exit(0);
	}

	protected void printFooter() {
		System.out.println("\n");
		System.out.println(SEPARATOR_HR);
		System.out.println(appGoodbye());
		System.out.println(SEPARATOR_HR);
	}

	protected void printHeader() {
		System.out.println(SEPARATOR_HR);
		System.out.println(appTitle() + " " + Application.VERSION);
		System.out.println(Application.COPYRIGHT);
		System.out.println(SEPARATOR_HR);
	}

	private final void clearEventHandlers() {
		try {
			doClearEventHandlers(dispatcher);

			dispatcher.shutdown();
		} catch (final Exception e) {
			LOGGER.error("Unable to cleanup event handlers", e);
		}
	}

	private final void setupEventHandlers() {
		doSetupEventHandlers(dispatcher);
	}

	protected abstract void doMain(final String[] args);

	protected abstract String appTitle();

	protected abstract String appGoodbye();

	protected void doClearEventHandlers(final EventDispatcher dispatcher) {
		// nothing to do
	}

	protected abstract void doSetupEventHandlers(EventDispatcher dispatcher);
}
