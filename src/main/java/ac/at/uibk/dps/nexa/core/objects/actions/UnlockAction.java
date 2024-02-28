package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.UnlockActionClass;
import java.util.Optional;

public class UnlockAction extends Action {

  private UnlockAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build an unlock action, given an unlock action class.
   *
   * @param unlockActionClass An unlock action class describing the unlock action to build.
   * @return The unlock action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(UnlockActionClass unlockActionClass) throws IllegalArgumentException {
    var action = new UnlockAction(unlockActionClass.name);

    return action;
  }
}
