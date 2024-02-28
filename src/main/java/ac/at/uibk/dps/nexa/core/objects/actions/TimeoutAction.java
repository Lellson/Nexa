package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.TimeoutActionClass;
import java.util.Optional;

public class TimeoutAction extends Action {

  private TimeoutAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build a timeout action, given a timeout action class.
   *
   * @param timeoutActionClass A timeout action class describing the timeout action to build.
   * @return The built timeout action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(TimeoutActionClass timeoutActionClass) throws IllegalArgumentException {
    var action = new TimeoutAction(timeoutActionClass.name);

    return action;
  }
}
