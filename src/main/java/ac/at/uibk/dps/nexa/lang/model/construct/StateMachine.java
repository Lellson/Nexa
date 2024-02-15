package ac.at.uibk.dps.nexa.lang.model.construct;

import ac.at.uibk.dps.nexa.lang.model.construct.action.Action;
import ac.at.uibk.dps.nexa.lang.model.construct.helper.StateOrStateMachine;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Map;
import java.util.Optional;

public class StateMachine extends Construct implements StateOrStateMachine {

  @NotNull
  public String name;

  @NotNull
  @Size(min = 1, message = "At least one state must be provided in 'states'")
  public StateOrStateMachine[] states;

  public Optional<String> description;

  public Optional<Map<String, String>> localContext;

  public Optional<Map<String, String>> persistentContext;

  public Optional<Guard[]> guards;

  public Optional<Action[]> actions;
}
