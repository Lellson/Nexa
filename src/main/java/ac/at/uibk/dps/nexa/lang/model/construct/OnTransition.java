package ac.at.uibk.dps.nexa.lang.model.construct;

import javax.validation.constraints.NotNull;

public class OnTransition extends Transition {

  @NotNull
  public String event;
}
