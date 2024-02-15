package ac.at.uibk.dps.nexa.lang.model.construct;

import ac.at.uibk.dps.nexa.lang.model.construct.action.Action;
import ac.at.uibk.dps.nexa.lang.model.construct.action.ActionReference;
import ac.at.uibk.dps.nexa.lang.model.construct.helper.StateOrStateMachine;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Map;
import java.util.Optional;

/**
 * State machine construct. Represents a state machine within a collaborative state machine.
 * <p>
 * Keywords:
 * <table border="1">
 *  <tr><th>Keyword</th><th>Description</th><th>Required</th></tr>
 *  <tr><td>name</td><td>Unique name</td><td>Yes</td></tr>
 *  <tr><td>description</td><td>Description</td><td>No</td></tr>
 *  <tr><td>localContext</td><td>Lexical description of the local context</td><td>No</td></tr>
 *  <tr><td>persistentContext</td><td>Lexical description of the persistent context</td><td>No</td></tr>
 *  <tr><td>guards</td><td>Named guards</td><td>No</td></tr>
 *  <tr><td>actions</td><td>Named actions</td><td>No</td></tr>
 * </table>
 * </p>
 * <p>
 *  Example:
 *  <pre>
 *    {
 *      "name": "Collaborative State Machine Name",
 *      "states": [...],
 *      "description": "Description",
 *      "localContext": {
 *        "variable": "value"
 *      },
 *      "persistentContext": {
 *        "variable": "value"
 *      },
 *      "guards": [],
 *      "actions": []
 *    }
 *  </pre>
 * </p>
 *
 * @since CSML 0.1.
 */
public class StateMachine extends Construct implements StateOrStateMachine {

  /**
   * The name.
   */
  @NotNull
  public String name;

  /**
   * The states.
   * <p>
   * At least one initial state must be provided.
   * </p>
   */
  @NotNull
  @Size(min = 1, message = "At least one state must be provided in 'states'")
  public StateOrStateMachine[] states;

  /**
   * The optional description.
   */
  public Optional<String> description;

  /**
   * The optional lexical declaration of local context variables.
   */
  public Optional<Map<String, String>> localContext;

  /**
   * The optional lexical declaration of persistent context variables.
   */
  public Optional<Map<String, String>> persistentContext;

  /**
   * The optional named guards.
   * <p>
   * The guards declared here may be used inside this state machine by referencing the names.
   * </p>
   *
   * @see StateMachine
   * @see GuardReference
   */
  public Optional<Guard[]> guards;

  /**
   * The optional named actions.
   * <p>
   * The actions declared here may be used inside this state machine by referencing the names.
   * </p>
   *
   * @see StateMachine
   * @see ActionReference
   */
  public Optional<Action[]> actions;
}
