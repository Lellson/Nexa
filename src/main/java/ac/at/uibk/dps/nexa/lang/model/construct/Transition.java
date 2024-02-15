package ac.at.uibk.dps.nexa.lang.model.construct;

import ac.at.uibk.dps.nexa.lang.model.construct.action.ActionOrReference;
import ac.at.uibk.dps.nexa.lang.model.construct.helper.GuardOrReference;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public class Transition extends Construct {

  @NotNull
  public String target;

  public Optional<GuardOrReference[]> guards;

  public Optional<ActionOrReference[]> actions;
}
