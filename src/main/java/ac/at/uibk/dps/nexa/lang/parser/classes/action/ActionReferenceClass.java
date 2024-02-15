package ac.at.uibk.dps.nexa.lang.parser.classes.action;

import jakarta.validation.constraints.NotNull;

public class ActionReferenceClass implements ActionOrReference {

  @NotNull
  public String reference;
}
