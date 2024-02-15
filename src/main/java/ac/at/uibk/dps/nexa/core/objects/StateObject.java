package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;

public class StateObject implements CheckedObject {

  @Override
  public CheckedObject validate() throws CheckerException {
    return this;
  }
}
