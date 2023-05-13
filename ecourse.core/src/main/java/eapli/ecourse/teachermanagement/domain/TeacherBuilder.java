package eapli.ecourse.teachermanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class TeacherBuilder implements DomainFactory<Teacher> {
  // TODO incomplete builder

  private SystemUser systemUser;
  private TaxPayerNumber taxPayerNumber;

  public TeacherBuilder withSystemUser(final SystemUser systemUser) {
    this.systemUser = systemUser;
    return this;
  }

  public TeacherBuilder withTaxPayerNumber(final TaxPayerNumber taxPayerNumber) {
    this.taxPayerNumber = taxPayerNumber;
    return this;
  }

  public TeacherBuilder withTaxPayerNumber(final String taxPayerNumber) {
    this.taxPayerNumber = new TaxPayerNumber(taxPayerNumber);
    return this;
  }

  @Override
  public Teacher build() {
    // since the factory knows that all the parts are needed it could throw
    // an exception. however, we will leave that to the constructor
    return new Teacher(this.systemUser, this.taxPayerNumber);
  }
}
