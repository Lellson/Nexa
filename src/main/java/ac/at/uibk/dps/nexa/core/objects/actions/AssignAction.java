package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.AssignActionClass;
import java.util.Optional;

public class AssignAction extends Action {

  private AssignAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build an assign action, given an assign action class.
   *
   * @param assignActionClass An assign action class describing the assign action to build.
   * @return The built assign action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(AssignActionClass assignActionClass) throws IllegalArgumentException {
    var action = new AssignAction(assignActionClass.name);

    return action;
  }
}
