package ac.at.uibk.dps.nexa.lang.model.keyword;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Version keyword.
 *
 * @since CSML 0.1.
 */
public enum Version {
  /**
   * Version 0.1.
   */
  @JsonProperty("0.1")
  ZERO_ONE;
}
