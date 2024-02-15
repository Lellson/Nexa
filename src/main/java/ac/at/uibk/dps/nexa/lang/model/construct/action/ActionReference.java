package ac.at.uibk.dps.nexa.lang.model.construct.action;

import jakarta.validation.constraints.NotNull;

public class ActionReference implements ActionOrReference {

  @NotNull
  public String reference;
}
