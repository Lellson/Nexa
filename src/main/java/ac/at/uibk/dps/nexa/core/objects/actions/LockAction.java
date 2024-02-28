package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.LockActionClass;
import java.util.Optional;

public class LockAction extends Action {

  private LockAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build a lock action, given a lock action class.
   *
   * @param lockActionClass A lock action class describing the lock action to build.
   * @return The built lock action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(LockActionClass lockActionClass) throws IllegalArgumentException {
    var action = new LockAction(lockActionClass.name);

    return action;
  }
}
