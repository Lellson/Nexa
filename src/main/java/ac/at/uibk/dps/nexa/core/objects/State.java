package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.parser.classes.StateClass;

public class State {

  public final String name;

  private State(String name) {
    this.name = name;
  }

  /**
   * Build a state, given a state class.
   *
   * @param stateClass A state class describing the state to build.
   * @return The built state if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static State build(StateClass stateClass)
      throws IllegalArgumentException {
    State state = new State(stateClass.name);

    // Validate the transition
    state.validate();

    return state;
  }

  private void validate() throws IllegalArgumentException {

  }
}
