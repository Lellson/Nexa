package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.InvokeActionClass;
import java.util.Optional;

public class InvokeAction extends Action {

  private InvokeAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build an invoke action, given an invoke action class.
   *
   * @param invokeActionClass An invoke action class describing the invoke action to build.
   * @return The built invoke action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(InvokeActionClass invokeActionClass) throws IllegalArgumentException {
    var action = new InvokeAction(invokeActionClass.name);

    return action;
  }
}
