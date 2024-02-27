package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionClass;

public class Action {

  private Action() {

  }

  /**
   * Build an action, given an action class.
   *
   * @param actionClass An action class describing the action to build.
   * @return The built action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(ActionClass actionClass)
      throws IllegalArgumentException {
    var action = new Action();

    return action;
  }

  private void validate() throws IllegalArgumentException {

  }
}
