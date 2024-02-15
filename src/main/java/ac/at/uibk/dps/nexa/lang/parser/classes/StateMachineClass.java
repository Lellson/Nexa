package ac.at.uibk.dps.nexa.lang.parser.classes;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionReferenceClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.helper.StateOrStateMachine;
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
 *      name: 'Collaborative State Machine Name',
 *      states: [...],
 *      localContext: {
 *        variable: 'value'
 *      },
 *      persistentContext: {
 *        variable: 'value'
 *      },
 *      guards: [],
 *      actions: []
 *    }
 *  </pre>
 * </p>
 *
 * @since CSML 0.1.
 */
public class StateMachineClass extends Construct implements StateOrStateMachine {

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
   * @see StateMachineClass
   * @see GuardReferenceClass
   */
  public Optional<GuardClass[]> guards;

  /**
   * The optional named actions.
   * <p>
   * The actions declared here may be used inside this state machine by referencing the names.
   * </p>
   *
   * @see StateMachineClass
   * @see ActionReferenceClass
   */
  public Optional<ActionClass[]> actions;
}
