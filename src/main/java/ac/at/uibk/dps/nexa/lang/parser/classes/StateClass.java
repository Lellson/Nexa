package ac.at.uibk.dps.nexa.lang.parser.classes;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionOrReference;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.RaiseActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.helper.StateOrStateMachine;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

/**
 * State construct, represents an atomic state of a state machine.
 * <p>
 * Keywords:
 * <table border="1">
 *  <tr><th>Keyword</th><th>Description</th><th>Required</th></tr>
 *  <tr><td>name</td><td>Unique name</td><td>Yes</td></tr>
 *  <tr><td>isInitial</td><td>Initial state flag</td><td>No</td></tr>
 *  <tr><td>isTerminal</td><td>Terminal state flag</td><td>No</td></tr>
 *  <tr><td>entry</td><td>On entry actions</td><td>No</td></tr>
 *  <tr><td>exit</td><td>On exit actions</td><td>No</td></tr>
 *  <tr><td>while</td><td>While actions</td><td>No</td></tr>
 *  <tr><td>after</td><td>Timeout actions</td><td>No</td></tr>
 *  <tr><td>on</td><td>On transitions</td><td>No</td></tr>
 *  <tr><td>always</td><td>Always transitions</td><td>No</td></tr>
 *  <tr><td>localContext</td><td>Lexical description of the local context</td><td>No</td></tr>
 *  <tr><td>persistentContext</td><td>Lexical description of the persistent context</td><td>No</td></tr>
 *  <tr><td>staticContext</td><td>Lexical description of the static context</td><td>No</td></tr>
 * </table>
 * </p>
 * <p>
 * Example:
 * <pre>
 *    {
 *      name: 'State Name',
 *      isInitial: true,
 *      isTerminal: false,
 *      entry: [...],
 *      exit: [...],
 *      while: [...],
 *      after: [...],
 *      on: [...],
 *      localContext: {
 *        variable: 'value'
 *      },
 *      persistentContext: {
 *        variable: 'value'
 *      },
 *      staticContext: {
 *        variable: 'value'
 *      }
 *    }
 * </pre>
 * </p>
 *
 * @since CSML 0.1.
 */
public class StateClass extends Construct implements StateOrStateMachine {

  /**
   * The name.
   * <p>
   * Can be referenced in a description.
   * </p>
   *
   * @see TransitionClass
   */
  @NotNull
  public String name;

  /**
   * The is initial flag. Indicating if this is the intial state of the state machine. Exactly one
   * state must be the initial state of a state machine. If omitted, the state is not initial.
   */
  @JsonSetter(nulls = Nulls.SKIP)
  public boolean isInitial;

  /**
   * The is terminal flag. Indicating if this is a terminal state of the state machine. If omitted,
   * the state is not terminal.
   */
  @JsonSetter(nulls = Nulls.SKIP)
  public boolean isTerminal;

  /**
   * The optional entry actions. Can be provided as action references to previously declared
   * actions, or inline actions.
   */
  public Optional<ActionOrReference[]> entry;

  /**
   * The optional exit actions. Can be provided as action references to previously declared actions,
   * or inline actions.
   */
  public Optional<ActionOrReference[]> exit;

  /**
   * The optional while actions. Can be provided as action references to previously declared
   * actions, or inline actions.
   */
  @JsonProperty("while")
  public Optional<ActionOrReference[]> whilee;

  /**
   * The optional after (timeout) actions. Can be provided as action references to previously
   * declared actions, or inline actions. Actions provided must be raise event actions.
   *
   * @see RaiseActionClass
   */
  public Optional<ActionOrReference[]> after;

  /**
   * The optional on transitions. On transitions are taken upon event receiving an event that
   * matches the 'event' keyword of the on transition.
   */
  public Optional<OnTransitionClass[]> on;

  /**
   * The optional always transitions. Always transitions are always taken upon entering a state.
   */
  public Optional<TransitionClass[]> always;

  /**
   * The optional lexical declaration of local context variables.
   */
  public Optional<Map<String, String>> localContext;

  /**
   * The optional lexical declaration of persistent context variables.
   */
  public Optional<Map<String, String>> persistentContext;

  /**
   * The optional lexical declaration of static context variables.
   */
  public Optional<Map<String, String>> staticContext;

  /**
   * Default State constructor.
   */
  public StateClass() {
    isInitial = false;
    isTerminal = false;
  }
}
