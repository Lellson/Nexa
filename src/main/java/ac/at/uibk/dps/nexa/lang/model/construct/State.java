package ac.at.uibk.dps.nexa.lang.model.construct;

import ac.at.uibk.dps.nexa.lang.model.construct.action.ActionOrReference;
import ac.at.uibk.dps.nexa.lang.model.construct.helper.StateOrStateMachine;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

/**
 * State CSML construct, represents an atomic state of a state machine.
 * <p>
 * Rules:
 * <ul>
 *   <li>A unique name is <b>required</b>.</li>
 * </ul>
 */
public class State extends Construct implements StateOrStateMachine {

  @NotNull
  public String name;

  @JsonSetter(nulls = Nulls.SKIP)
  public boolean isInitial;

  @JsonSetter(nulls = Nulls.SKIP)
  public boolean isTerminal;

  public Optional<String> description;

  public Optional<ActionOrReference[]> entry;

  public Optional<ActionOrReference[]> exit;

  @JsonProperty("while")
  public Optional<ActionOrReference[]> whilee;

  public Optional<ActionOrReference[]> after;

  public Optional<OnTransition[]> on;

  public Optional<Transition[]> always;

  public Optional<Map<String, String>> localContext;

  public Optional<Map<String, String>> persistentContext;

  public Optional<Map<String, String>> staticContext;

  public State() {
    isInitial = false;
    isTerminal = false;
  }
}
