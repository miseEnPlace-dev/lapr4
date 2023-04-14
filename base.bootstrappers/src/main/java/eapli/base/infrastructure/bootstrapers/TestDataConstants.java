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
package eapli.base.infrastructure.bootstrapers;

import java.util.Calendar;

import eapli.framework.time.util.Calendars;

public final class TestDataConstants {

    public static final String DISH_TYPE_MEAT = "meat";
    public static final String DISH_TYPE_FISH = "fish";
    public static final String DISH_TYPE_VEGIE = "vegie";

    public static final String ALLERGEN_CRUSTACEOS = "crustaceos";
    public static final String ALLERGEN_PEIXES = "peixes";
    public static final String ALLERGEN_GLUTEN = "gluten";

    public static final String DISH_NAME_COSTELETA_A_SALSICHEIRO = "costeleta à salsicheiro";
    public static final String DISH_NAME_PICANHA = "picanha";
    public static final String DISH_NAME_LAGOSTA_SUADA = "lagosta suada";
    public static final String DISH_NAME_BACALHAU_A_BRAZ = "bacalhau à braz";
    public static final String DISH_NAME_LENTILHAS_SALTEADAS = "lentilhas salteadas";
    public static final String DISH_NAME_TOFU_GRELHADO = "tofu grelhado";

    public static final String USER_TEST1 = "user1";

    @SuppressWarnings("squid:S2068")
    public static final String PASSWORD1 = "Password1";

    @SuppressWarnings("squid:S2885")
    public static final Calendar DATE_TO_BOOK = Calendars.of(2017, 12, 01);

    private TestDataConstants() {
        // ensure utility
    }
}
