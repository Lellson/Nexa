package ac.at.uibk.dps.nexa.lang.model.construct.action;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Channel {
  @JsonProperty("internal")
  INTERNAL,

  @JsonProperty("external")
  EXTERNAL,

  @JsonProperty("global")
  GLOBAL
}
