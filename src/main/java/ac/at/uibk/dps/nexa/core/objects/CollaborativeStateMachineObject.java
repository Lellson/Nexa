package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import ac.at.uibk.dps.nexa.lang.parser.classes.CollaborativeStateMachineClass;
import ac.at.uibk.dps.nexa.lang.parser.keywords.MemoryMode;
import java.util.Arrays;
import java.util.Optional;
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

  /**
   * Returns a state machine object by its name. If no state machine object is known with the
   * supplied name, empty is returned.
   *
   * @param name Name of the state machine object to return.
   * @return The state machine object with the supplied name or empty.
   */
  public Optional<StateMachineObject> getStateMachineObjectByName(String name) {
    // Attempt to match the provided name to a known state object
    var states = stateMachineObjects.stream()
        .filter(stateObject -> stateObject.getName().equals(name))
        .toList();

    // Expect precisely one state object with the provided name
    if (states.size() != 1) {
      return Optional.empty();
    }
    return Optional.ofNullable(states.getFirst());
  }

  @Override
  public CheckedObject validate() throws CheckerException {
    for (var stateMachineObject : stateMachineObjects) {
      stateMachineObject.validate();
    }

    return this;
  }

  private void build(CollaborativeStateMachineClass c) {
    // Construct state machine objects declared in the collaborative state machine class
    stateMachineObjects = Arrays.stream(c.stateMachines)
        .map(StateMachineObject::build)
        .collect(Collectors.toSet());
  }
}
