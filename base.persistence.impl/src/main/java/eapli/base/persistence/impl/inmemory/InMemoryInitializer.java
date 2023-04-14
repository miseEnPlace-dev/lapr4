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
package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;

/**
 * A static initialiser to make sure there is the default bootstrapping data,
 * namely power user, when using the in memory repositories.
 * <p>
 * Since we are using an in memory implementation of the persistence, the
 * bootstrap data will be lost at the end of executing the bootstrap
 * application. This helper class initialises the same data as the bootstrapper
 * in the other applications.
 * <p>
 * Note that this is just for demo purposes and in a real scenario you would use
 * a persistent mechanism like a database or file system in order not to loose
 * data.
 * <p>
 *
 * Each repository must call this static initialiser, e.g.:
 *
 * <pre>
 * <code>
 * public class InMemoryMaterialRepository extends InMemoryDomainRepository<String, Material>
 *       implements MaterialRepository {
 *
 *    static {
 *      InMemoryInitializer.init();
 *    }
 * }
 * </code>
 * </pre>
 *
 * @author Paulo Gandra de Sousa
 */
final class InMemoryInitializer {

  private static class LazyHolder {
    private static final InMemoryInitializer INSTANCE = new InMemoryInitializer();

    private LazyHolder() {
    }
  }

  private InMemoryInitializer() {
    // to ensure some default test data is available, specially when using
    // in memory persistence
    new BaseBootstrapper().execute();
  }

  private void initialize() {
    // nothing to do; data has already been initialized in the singleton
    // constructor.
  }

  public static void init() {
    LazyHolder.INSTANCE.initialize();
  }
}
