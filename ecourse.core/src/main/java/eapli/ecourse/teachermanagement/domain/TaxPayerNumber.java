package eapli.ecourse.teachermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

public class TaxPayerNumber implements ValueObject, Comparable<TaxPayerNumber> {
  private static final long serialVersionUID = 1L;

  private String number;

  protected TaxPayerNumber(final String taxPayerNumber) {
    if (StringPredicates.isNullOrEmpty(taxPayerNumber))
      throw new IllegalArgumentException(
          "Tax payer number Number should neither be null nor empty");

    // TODO validate invariants, i.e., tax payer number number regular expression
    this.number = taxPayerNumber;
  }

  protected TaxPayerNumber() {
    // for ORM
  }

  public static TaxPayerNumber valueOf(final String taxPayerNumber) {
    return new TaxPayerNumber(taxPayerNumber);
  }

  @Override
  public String toString() {
    return this.number;
  }

  @Override
  public int compareTo(final TaxPayerNumber arg0) {
    return number.compareTo(arg0.number);
  }

  @Override
  public int hashCode() {
    return number.hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null || !(obj instanceof TaxPayerNumber)) {
      return false;
    }

    final TaxPayerNumber other = (TaxPayerNumber) obj;

    return this.number.equals(other.number);
  }
}
