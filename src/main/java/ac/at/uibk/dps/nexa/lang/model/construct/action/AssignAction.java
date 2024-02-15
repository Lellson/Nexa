package ac.at.uibk.dps.nexa.lang.model.construct.action;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class AssignAction extends Action {

  @NotNull
  public String variable;

  @NotNull
  public String value;
}
