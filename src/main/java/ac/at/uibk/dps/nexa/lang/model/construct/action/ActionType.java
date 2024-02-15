package ac.at.uibk.dps.nexa.lang.model.construct.action;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ActionType {
  @JsonProperty("invoke")
  Invoke,

  @JsonProperty("create")
  Create,

  @JsonProperty("assign")
  Assign,

  @JsonProperty("lock")
  Lock,

  @JsonProperty("unlock")
  Unlock,

  @JsonProperty("raise")
  Raise,

  @JsonProperty("timeout")
  Timeout,

  @JsonProperty("timeoutReset")
  TimeoutReset,

  @JsonProperty("match")
  Match;
}
