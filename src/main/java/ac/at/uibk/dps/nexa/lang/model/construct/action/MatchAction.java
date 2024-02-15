package ac.at.uibk.dps.nexa.lang.model.construct.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class MatchAction extends Action {

  @NotNull
  public String value;

  @JsonProperty("case")
  public Map<String, ActionOrReference> casee;
}
