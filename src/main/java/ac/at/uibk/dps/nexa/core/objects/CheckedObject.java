package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;

public abstract class CheckedObject {

  public CheckedObject() throws CheckerException {
    validate();
  }

  abstract void validate() throws CheckerException;
}
