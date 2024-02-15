package ac.at.uibk.dps.nexa.lang.model.keyword;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Memory mode CSML keyword.
 * <p>
 * Rules:
 * <ul>
 *   <li>One of the following values is <b>required</b>: <i>distributed</i>, <i>shared</i>.</li>
 * </ul>
 *
 * @since CSML One.
 */
public enum MemoryMode {
  /**
   * Distributed memory mode.
   */
  @JsonProperty("distributed")
  DISTRIBUTED,

  /**
   * Shared memory mode.
   */
  @JsonProperty("shared")
  SHARED;
}
