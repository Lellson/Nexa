package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.RaiseActionClass;
import java.util.Optional;

public class RaiseAction extends Action {

  private RaiseAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build a raise action, given a raise action class.
   *
   * @param raiseActionClass A raise action class describing the raise action to build.
   * @return The built raise action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(RaiseActionClass raiseActionClass) throws IllegalArgumentException {
    var action = new RaiseAction(raiseActionClass.name);

    return action;
  }
}
