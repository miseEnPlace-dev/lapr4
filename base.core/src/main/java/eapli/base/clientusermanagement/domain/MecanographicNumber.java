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
package eapli.base.clientusermanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Embeddable
public class MecanographicNumber implements ValueObject, Comparable<MecanographicNumber> {

  private static final long serialVersionUID = 1L;

  private String number;

  public MecanographicNumber(final String mecanographicNumber) {
    if (StringPredicates.isNullOrEmpty(mecanographicNumber)) {
      throw new IllegalArgumentException(
          "Mecanographic Number should neither be null nor empty");
    }
    // TODO validate invariants, i.e., mecanographic number regular
    // expression
    this.number = mecanographicNumber;
  }

  protected MecanographicNumber() {
    // for ORM
  }

  public static MecanographicNumber valueOf(final String mecanographicNumber) {
    return new MecanographicNumber(mecanographicNumber);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MecanographicNumber)) {
      return false;
    }

    final MecanographicNumber that = (MecanographicNumber) o;
    return this.number.equals(that.number);
  }

  @Override
  public int hashCode() {
    return this.number.hashCode();
  }

  @Override
  public String toString() {
    return this.number;
  }

  @Override
  public int compareTo(final MecanographicNumber arg0) {
    return number.compareTo(arg0.number);
  }
}
