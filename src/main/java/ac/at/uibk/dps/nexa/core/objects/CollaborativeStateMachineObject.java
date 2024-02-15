package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import ac.at.uibk.dps.nexa.lang.parser.classes.CollaborativeStateMachineClass;
import ac.at.uibk.dps.nexa.lang.parser.keywords.MemoryMode;

public class CollaborativeStateMachineObject extends CheckedObject {

  public final String name;

  public final MemoryMode memoryMode;

  private StateMachineObject[] stateMachineObjects;

  public CollaborativeStateMachineObject(CollaborativeStateMachineClass c) throws CheckerException {
    super();

    this.name = c.name;
    this.memoryMode = c.memoryMode;
  }

  @Override
  void validate() throws CheckerException {

  }
}
