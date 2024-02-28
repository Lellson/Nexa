package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.CreateActionClass;
import java.util.Optional;

public class CreateAction extends Action {

  private CreateAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build a create action, given a create action class.
   *
   * @param createActionClass A create action class describing the create action to build.
   * @return The built create action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(CreateActionClass createActionClass) throws IllegalArgumentException {
    var action = new CreateAction(createActionClass.name);

    return action;
  }
}
