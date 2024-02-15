package ac.at.uibk.dps.nexa.lang.model.construct;

import ac.at.uibk.dps.nexa.lang.model.construct.helper.GuardOrReference;
import jakarta.validation.constraints.NotNull;

public class GuardReference implements GuardOrReference {

  @NotNull
  public String reference;
}
