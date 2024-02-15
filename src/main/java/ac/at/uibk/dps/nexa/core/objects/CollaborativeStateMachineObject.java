package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import ac.at.uibk.dps.nexa.lang.parser.classes.CollaborativeStateMachineClass;
import ac.at.uibk.dps.nexa.lang.parser.keywords.MemoryMode;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CollaborativeStateMachineObject implements CheckedObject {

  public final String name;

  public final MemoryMode memoryMode;

  private Set<StateMachineObject> stateMachineObjects;

  public CollaborativeStateMachineObject(CollaborativeStateMachineClass c) throws CheckerException {
    super();

    name = c.name;
    memoryMode = c.memoryMode;

    build(c);
  }

  private void build(CollaborativeStateMachineClass c) {
    stateMachineObjects = Arrays.stream(c.stateMachines)
        .map(StateMachineObject::build)
        .collect(Collectors.toSet());
  }

  @Override
  public CheckedObject validate() throws CheckerException {
    for (var stateMachineObject : stateMachineObjects) {
      stateMachineObject.validate();
    }

    return this;
  }
}
