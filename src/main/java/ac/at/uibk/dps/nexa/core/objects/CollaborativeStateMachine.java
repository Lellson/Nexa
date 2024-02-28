package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.parser.classes.CollaborativeStateMachineClass;
import ac.at.uibk.dps.nexa.lang.parser.keywords.MemoryMode;
import java.util.Arrays;
import java.util.Optional;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

public class CollaborativeStateMachine extends DirectedMultigraph<StateMachine, DefaultEdge> {

  public final String name;

  public final MemoryMode memoryMode;

  private CollaborativeStateMachine(String name, MemoryMode memoryMode) {
    super(DefaultEdge.class);

    this.name = name;
    this.memoryMode = memoryMode;
  }

  /**
   * Build a collaborative state machine, given a state machine class.
   *
   * @param collaborativeStateMachineClass A  collaborativestate machine class describing the collaborative state
   *                                       machine to build.
   * @return The built collaborative state machine if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static CollaborativeStateMachine build(CollaborativeStateMachineClass collaborativeStateMachineClass)
      throws IllegalArgumentException {
    var collaborativeStateMachine = new CollaborativeStateMachine(collaborativeStateMachineClass.name,
        collaborativeStateMachineClass.memoryMode);

    // Attempt to add vertices
    Arrays.stream(collaborativeStateMachineClass.stateMachines)
        .map(stateMachineClass -> StateMachine.build(stateMachineClass))
        .forEach(collaborativeStateMachine::addVertex);

    return collaborativeStateMachine;
  }

  /**
   * Returns a state machine by its name. If no state machine is known with the supplied name, empty is returned.
   *
   * @param name Name of the state machine to return.
   * @return The state machine with the supplied name or empty.
   */
  public Optional<StateMachine> getStateMachineByName(String name) {
    // Attempt to match the provided name to a known state machine
    var states = vertexSet().stream()
        .filter(state -> state.name.equals(name))
        .toList();

    // Expect precisely one state machine with the provided name
    if (states.size() != 1) {
      return Optional.empty();
    }
    return Optional.ofNullable(states.getFirst());
  }
}
