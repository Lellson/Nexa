package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.core.Common;
import ac.at.uibk.dps.nexa.core.objects.actions.Action;
import ac.at.uibk.dps.nexa.core.objects.helper.ActionResolver;
import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import ac.at.uibk.dps.nexa.lang.checker.CheckerException.Message;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateMachineClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.TransitionClass;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import org.jgrapht.graph.DirectedPseudograph;

public class StateMachine extends DirectedPseudograph<State, Transition> {

  public final String name;

  public final Optional<Action[]> actions;

  private StateMachine(String name, Optional<Action[]> actions) {
    super(Transition.class);

    this.name = name;
    this.actions = actions;
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
    // Construct the list of named actions of this state machine, or leave empty if no named actions are declared
    var actions = stateMachineClass.actions.map(actionClasses -> Arrays.stream(actionClasses)
        .map(Action::build)
        .toArray(Action[]::new));

    // Ensure that no duplicate entries exist
    actions.ifPresent(a -> Common.getArrayDuplicates(a).stream()
        .forEach(action -> {
          throw new IllegalArgumentException(new CheckerException(Message.ACTION_NAME_IS_NOT_UNIQUE, action.name));
        }));

    // Create this state machine
    var stateMachine = new StateMachine(stateMachineClass.name, actions);

    // Create an action resolver, used to resolve the named actions of a state machine in states and transitions
    var actionResolver = new ActionResolver(stateMachine);

    // Attempt to add vertices
    Arrays.stream(stateMachineClass.states)
        .filter(StateClass.class::isInstance)
        .map(stateOrStateMachineClass -> State.build((StateClass) stateOrStateMachineClass, actionResolver))
        .forEach(stateMachine::addVertex);

    // Attempt to add edges
    Arrays.stream(stateMachineClass.states)
        .filter(StateClass.class::isInstance)
        .map(StateClass.class::cast)
        .forEach(stateClass -> {
          // Acquire source node, this is expected to always succeed as we use the previously created state
          var source = stateMachine.getStateByName(stateClass.name);

          BiConsumer<BiFunction<TransitionClass, ActionResolver, Transition>, TransitionClass[]> processTransitions = (transitionSupplier, on) -> {
            for (var transitionClass : on) {
              // Acquire the target node
              var target = stateMachine.getStateByName(transitionClass.target);

              // Attempt to add an edge to the state machine graph that resembles the transition
              if (!stateMachine.addEdge(source, target, transitionSupplier.apply(transitionClass, actionResolver))) {
                throw new IllegalArgumentException(
                    new CheckerException(Message.ILLEGAL_STATE_MACHINE_GRAPH, source.name, target.name));
              }
            }
          };

          // Attempt to add edges corresponding to the "on" transitions, these transitions are optional
          stateClass.on.ifPresent(
              on -> {
                // Ensure that "on" transitions have distinct events
                var hasDuplicateEdges = Arrays.stream(on)
                    .collect(Collectors.groupingBy(
                        transitionClass -> transitionClass.event, Collectors.counting())
                    ).entrySet().stream()
                    .anyMatch(entry -> entry.getValue() > 1);

                if (hasDuplicateEdges) {
                  throw new IllegalArgumentException(
                      new CheckerException(Message.MULTIPLE_TRANSITIONS_WITH_SAME_EVENT, stateClass.name));
                }
                processTransitions.accept(OnTransition::new, on);
              }
          );

          // Attempt to add edges corresponding to the "always" transitions, these transitions are optional
          stateClass.always.ifPresent(always -> processTransitions.accept(Transition::new, always));
        });

    return stateMachine;
  }

  /**
   * Returns the collection of events handled by this state machine.
   *
   * @return Events handled by this state machine.
   */
  public String[] getHandledEvents() {
    return edgeSet().stream()
        .filter(OnTransition.class::isInstance)
        .map(onTransition -> ((OnTransition) onTransition).event)
        .toArray(String[]::new);
  }

  /**
   * Returns a state by its name. If not one state is known with the supplied name, empty is returned.
   *
   * @param stateName Name of the state to return.
   * @return The state with the supplied name or empty.
   * @throws IllegalArgumentException In case not one state is known with the supplied name.
   */
  public State getStateByName(String stateName) {
    // Attempt to match the provided name to a known state
    var states = vertexSet().stream()
        .filter(state -> state.name.equals(stateName))
        .toList();

    // Expect precisely one state with the provided name
    if (states.isEmpty()) {
      throw new IllegalArgumentException(new CheckerException(Message.STATE_NAME_DOES_NOT_EXIST, stateName));
    } else if (states.size() != 1) {
      throw new IllegalArgumentException(new CheckerException(Message.STATE_NAME_IS_NOT_UNIQUE, stateName));
    }
    return states.getFirst();
  }

  /**
   * Returns an action by its name. If not one action is known with the supplied name, empty is returned.
   *
   * @param actionName Name of the action to return.
   * @return The action with the supplied name or empty.
   * @throws IllegalArgumentException In case not one action is known with the supplied name.
   */
  public Action getActionByName(String actionName) throws IllegalArgumentException {
    // Ensure that named actions are declared and an action with the provided name exists
    var actionsWithName = Arrays.stream(actions
            .orElseThrow(
                () -> new IllegalArgumentException(new CheckerException(Message.STATE_MACHINE_HAS_NO_NAMED_ACTIONS, name))))
        .filter(action -> action.name.equals(Optional.of(actionName)))
        .toList();

    // Ensure that precisely one action is known with this name
    if (actionsWithName.isEmpty()) {
      throw new IllegalArgumentException(new CheckerException(Message.NAMED_ACTION_DOES_NOT_EXIST, name, actionName));
    } else if (actionsWithName.size() != 1) {
      throw new IllegalArgumentException(new CheckerException(Message.ACTION_NAME_IS_NOT_UNIQUE, name, actionName));
    }
    return actionsWithName.getFirst();
  }
}
