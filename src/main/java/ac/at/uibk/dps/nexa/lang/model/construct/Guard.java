package ac.at.uibk.dps.nexa.lang.model.construct;

import ac.at.uibk.dps.nexa.lang.model.construct.helper.GuardOrReference;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Guard CSML construct. Represents a conditional (if) that determines if a transition can be
 * taken.
 * <p>
 * Requirements:
 * <ul>
 *  <li>A name must be provided and must be distinct.</li>
 *  <li>An expression but be provided that evaluated to a boolean value.</li>
 * </ul>
 * </p>
 * <p>
 * Optional:
 * <ul>
 *   <li>A description can be provided.</li>
 * </ul>
 * </p>
 * <p>
 * Example:
 * <pre>
 *   {
 *     "name": "Guard Name",
 *     "expression": "a==5"
 *   }
 * </pre>
 * </p>
 *
 * @since CSML One.
 */
public class Guard extends Construct implements GuardOrReference {

  /**
   * The guard name. Can be referenced inside a description.
   */
  @NotNull
  public String name;

  /**
   * An expression. Must evaluate to a boolean value.
   */
  @NotNull
  public String expression;

  public Optional<String> description;
}
