package ac.at.uibk.dps.nexa.lang.parser.classes;

import ac.at.uibk.dps.nexa.lang.parser.classes.action.ActionOrReference;
import ac.at.uibk.dps.nexa.lang.parser.classes.helper.GuardOrReference;
import java.util.Optional;
import javax.validation.constraints.NotNull;

/**
 * Transition construct. Represents a transition that is to be taken regardless of an event.
 * <p>
 * Keywords:
 * <table border="1">
 *  <tr><th>Keyword</th><th>Description</th><th>Required</th></tr>
 *  <tr><td>target</td><td>Target state</td><td>Yes</td></tr>
 *  <tr><td>guards</td><td>Guards</td><td>Yes</td></tr>
 *  <tr><td>actions</td><td>Actions</td><td>Yes</td></tr>
 * </table>
 * </p>
 *  Example:
 *  <pre>
 *    {
 *      target: 'State Name',
 *      guards: [...],
 *      actions: [...]
 *    }
 *  </pre>
 * </p>
 *
 * @since CSML 0.1.
 */
public class TransitionClass extends Construct {

  @NotNull
  public String target;

  /**
   * The optional guards. All guard expression need to evaluate to true before a transitions can be
   * taken. Can be provided as guard references to previously declared guards, or inline guards.
   *
   * @see StateMachineClass
   * @see GuardReferenceClass
   */
  public Optional<GuardOrReference[]> guards;

  /**
   * The optional actions. These actions are executed during the transition, if the transition is
   * taken. Can be provided as action references to previously declared actions, or inline actions.
   *
   * @see StateMachineClass
   * @see GuardReferenceClass
   */
  public Optional<ActionOrReference[]> actions;
}
