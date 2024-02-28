package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.core.objects.actions.Action;
import ac.at.uibk.dps.nexa.core.objects.helper.ActionResolver;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionOrActionReferenceClass;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class State {

  public final String name;

  public final ActionGraph entry;

  public final ActionGraph exit;

  public final ActionGraph whilee;

  private State(String name, Action[] entryActions, Action[] exitActions, Action[] whileActions) {
    this.name = name;

    this.entry = ActionGraph.build(entryActions);
    this.exit = ActionGraph.build(exitActions);
    this.whilee = ActionGraph.build(whileActions);
  }

  /**
   * Build a state, given a state class.
   *
   * @param stateClass A state class describing the state to build.
   * @return The built state if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static State build(StateClass stateClass, ActionResolver actionResolver)
      throws IllegalArgumentException {
    // Resolve actions
    Function<Optional<ActionOrActionReferenceClass[]>, Action[]> resolveActions = (Optional<ActionOrActionReferenceClass[]> actions) ->
        Arrays.stream(actions.orElse(new ActionOrActionReferenceClass[0]))
            .map(actionResolver::resolve)
            .toArray(Action[]::new);

    var entryActions = resolveActions.apply(stateClass.entry);
    var exitActions = resolveActions.apply(stateClass.exit);
    var whileActions = resolveActions.apply(stateClass.whilee);

    // Create this state
    var state = new State(stateClass.name, entryActions, exitActions, whileActions);

    return state;
  }
}
