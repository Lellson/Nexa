package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateMachineClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.TransitionClass;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import org.jgrapht.graph.DefaultDirectedGraph;

public class StateMachineObject extends
    DefaultDirectedGraph<StateObject, TransitionObject> implements
    CheckedObject {

  private String name;

  public StateMachineObject(String name) {
    super(TransitionObject.class);

    this.name = name;
  }

  public StateMachineObject(Supplier<StateObject> vertexSupplier,
      Supplier<TransitionObject> edgeSupplier, boolean weighted) {
    super(vertexSupplier, edgeSupplier, weighted);
  }

  public String getName() {
    return name;
  }

  /**
   * Returns a state object by its name. If no state object is known with the supplied name, empty
   * is returned.
   *
   * @param name Name of the state object to return.
   * @return The state object with the supplied name or empty.
   */
  public Optional<StateObject> getStateObjectByName(String name) {
    // Attempt to match the provided name to a known state object
    var states = vertexSet().stream()
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
    return this;
  }

  /**
   * Build a state machine object, given a state machine class.
   *
   * @param stateMachineClass A state machine class describing the state machine object to build.
   * @return The built state machine object if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various
   *                                  reasons.
   */
  public static StateMachineObject build(StateMachineClass stateMachineClass)
      throws IllegalArgumentException {
    StateMachineObject stateMachineObject = new StateMachineObject(stateMachineClass.name);

    // Attempt to add vertices
    Arrays.stream(stateMachineClass.states)
        .filter(StateClass.class::isInstance)
        .map(stateOrStateMachine -> StateObject.build((StateClass) stateOrStateMachine))
        .forEach(stateMachineObject::addVertex);

    // Attempt to add edges
    Arrays.stream(stateMachineClass.states)
        .filter(StateClass.class::isInstance)
        .map(StateClass.class::cast)
        .forEach(stateClass -> {
          // Acquire source node, this is expected to always succeed as we use the previously created state
          var source = stateMachineObject.getStateObjectByName(stateClass.name);
          source.orElseThrow(() ->
              new IllegalArgumentException(
                  String.format("A state with the name '%s' does not exist", stateClass.name)));

          BiConsumer<Supplier<TransitionObject>, TransitionClass[]> processTransitions = (transitionObjectSupplier, on) -> {
            Arrays.stream(on)
                .map(transitionClass -> stateMachineObject.getStateObjectByName(
                        transitionClass.target)
                    .orElseThrow(() ->
                        new IllegalArgumentException(
                            String.format("A state with the name '%s' does not exist",
                                transitionClass.target))))
                .forEach(target -> {
                  // Attempt to add edge between the source and target node to this state machine graph, edges
                  // should not be duplicate
                  if (!stateMachineObject.addEdge(source.get(), target,
                      transitionObjectSupplier.get())) {
                    throw new IllegalArgumentException(String.format(
                        "An attempt was made to construct an illegal state machine graph, tried to add an edge between the states '%s' and '%s'",
                        source, target));
                  }
                });
          };

          // Attempt to add edges corresponding to the "on" and "always" transitions. Note that these transitions are optional
          stateClass.on.ifPresent(
              on -> processTransitions.accept(OnTransitionObject::new, on));

          stateClass.always.ifPresent(
              always -> processTransitions.accept(AlwaysTransitionObject::new, always));
        });

    return stateMachineObject;
  }
}
