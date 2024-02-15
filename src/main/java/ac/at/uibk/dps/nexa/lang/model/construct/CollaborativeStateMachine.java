package ac.at.uibk.dps.nexa.lang.model.construct;

import ac.at.uibk.dps.nexa.lang.model.keyword.MemoryMode;
import ac.at.uibk.dps.nexa.lang.model.keyword.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Map;
import java.util.Optional;

/**
 * Collaborative state machine CSML construct. Represents the highest level entity in a
 * description.
 * <p>
 * Requirements:
 * <ul>
 *   <li>A name bust be provided and must be distinct.</li>
 *   <li>A valid version UUID must be provided.</li>
 *   <li>A valid memory mode must be provided.</li>
 *   <li>At least one state machine must be provided.</li>
 * </ul>
 * </p>
 * <p>
 * Optional:
 * <ul>
 *   <li>A description can be provided.</li>
 *   <li>The variables to lexically create in the local context of this collaborative state machine can be provided.</li>
 *   <li>The variables to lexically create in the persistent context of this collaborative state machine can be provided.</li>
 * </ul>
 * </p>
 * <p>
 * The following CSML versions are valid:
 * <table border="1">
 *   <tr>
 *     <td>Version One</td><td>e2e9c29b-867a-412e-81fb-2e0eda56d69a</td>
 *   </tr>
 * </table>
 * </p>
 * <p>
 * The following memory modes are valid:
 * <table border="1">
 *    <tr>
 *      <td>Distributed memory mode</td><td>distributed</td>
 *    </tr>
 *    <tr>
 *      <td>Shared memory mode</td><td>shared</td>
 *    </tr>
 *  </table>
 * </p>
 * <p>
 *  Example:
 *  <pre>
 *    {
 *      "name": "Collaborative State Machine Name",
 *      "version": "e2e9c29b-867a-412e-81fb-2e0eda56d69a",
 *      "memoryMode": "distributed",
 *      "stateMachines": [...],
 *      "description": "Description",
 *    }
 *  </pre>
 * </p>
 */
public class CollaborativeStateMachine extends Construct {

  @NotNull
  public String name;

  @NotNull
  public Version version;

  @NotNull
  public MemoryMode memoryMode;

  @NotNull
  @Size(min = 1, message = "At least one state machine must be provided in 'stateMachines'")
  public StateMachine[] stateMachines;

  public Optional<String> description;

  public Optional<Map<String, String>> localContext;

  public Optional<Map<String, String>> persistentContext;
}
