package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import ac.at.uibk.dps.nexa.lang.checker.CheckerException.Message;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateMachineClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.TransitionClass;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jgrapht.graph.DirectedPseudograph;

public class StateMachine extends DirectedPseudograph<State, Transition> {

  public final String name;

  private StateMachine(String name) {
    super(Transition.class);

    this.name = name;
  }

  /**
   * Build a state machine, given a state machine class.
   *
   * @param stateMachineClass A state machine class describing the state machine to build.
   * @return The built state machine if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static StateMachine build(StateMachineClass stateMachineClass)
      throws IllegalArgumentException {
    var stateMachine = new StateMachine(stateMachineClass.name);

    // Attempt to add vertices
    Arrays.stream(stateMachineClass.states)
        .filter(StateClass.class::isInstance)
        .map(stateOrStateMachineClass -> State.build((StateClass) stateOrStateMachineClass))
        .forEach(stateMachine::addVertex);

    // Attempt to add edges
    Arrays.stream(stateMachineClass.states)
        .filter(StateClass.class::isInstance)
        .map(StateClass.class::cast)
        .forEach(stateClass -> {
          // Acquire source node, this is expected to always succeed as we use the previously created state
          var source = stateMachine.getStateByName(stateClass.name)
              .orElseThrow(() -> new IllegalArgumentException(
                  new CheckerException(Message.STATE_NAME_DOES_NOT_EXIST, stateClass.name)));

          BiConsumer<Function<TransitionClass, Transition>, TransitionClass[]> processTransitions = (transitionSupplier, on) -> {
            for (var transitionClass : on) {
              // Acquire the target node
              var target = stateMachine.getStateByName(transitionClass.target)
                  .orElseThrow(() -> new IllegalArgumentException(
                      new CheckerException(Message.STATE_NAME_DOES_NOT_EXIST, transitionClass.target)));

              // Attempt to add an edge to the state machine graph that resembles the transition
              if (!stateMachine.addEdge(source, target, transitionSupplier.apply(transitionClass))) {
                throw new IllegalArgumentException(
                    new CheckerException(Message.ILLEGAL_STATE_MACHINE_GRAPH, source.name,
                        target.name));
              }
            }
          };

          // Attempt to add edges corresponding to the "on" transitions, these transitions are optional
          stateClass.on.ifPresent(
              on -> {
                // Ensure that "on" transitions have distinct events
                if (Arrays.stream(on)
                    .collect(Collectors.groupingBy(
                        transitionClass -> transitionClass.event, Collectors.counting())
                    ).entrySet().stream()
                    .anyMatch(entry -> entry.getValue() > 1)) {
                  throw new IllegalArgumentException(
                      new CheckerException(Message.MULTIPLE_TRANSITIONS_WITH_SAME_EVENT,
                          stateClass.name));
                }
                processTransitions.accept(OnTransition::new, on);
              }
          );

          // Attempt to add edges corresponding to the "always" transitions, these transitions are optional
          stateClass.always.ifPresent(always -> processTransitions.accept(Transition::new, always));
        });

    // Validate the state machine
    stateMachine.validate();

    return stateMachine;
  }

  /**
   * Returns a state by its name. If no state is known with the supplied name, empty is returned.
   *
   * @param name Name of the state to return.
   * @return The state with the supplied name or empty.
   */
  public Optional<State> getStateByName(String name) {
    // Attempt to match the provided name to a known state
    var states = vertexSet().stream()
        .filter(state -> state.name.equals(name))
        .toList();

    // Expect precisely one state with the provided name
    if (states.size() != 1) {
      return Optional.empty();
    }
    return Optional.ofNullable(states.getFirst());
  }

  private void validate() throws IllegalArgumentException {
  }
}
