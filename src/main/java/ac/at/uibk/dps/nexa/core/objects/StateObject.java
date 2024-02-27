package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateClass;

public class StateObject implements CheckedObject {

  private String name;

  public StateObject(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public CheckedObject validate() throws CheckerException {
    return this;
  }

  public static StateObject build(StateClass stateClass) {
    StateObject stateObject = new StateObject(stateClass.name);

    return stateObject;
  }
}
