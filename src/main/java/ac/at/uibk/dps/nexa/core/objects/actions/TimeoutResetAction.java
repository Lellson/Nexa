package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.TimeoutResetActionClass;
import java.util.Optional;

public class TimeoutResetAction extends Action {

  private TimeoutResetAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build a timeout reset action, given a timeout reset action class.
   *
   * @param timeoutResetActionClass A timeout reset action class describing the timeout reset action to build.
   * @return The built timeout reset action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(TimeoutResetActionClass timeoutResetActionClass) throws IllegalArgumentException {
    var action = new TimeoutResetAction(timeoutResetActionClass.name);

    return action;
  }
}
