package ac.at.uibk.dps.nexa.lang.model.construct.action;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class CreateAction extends Action {

  @NotNull
  public String variable;

  @NotNull
  public String value;

  @JsonSetter(nulls = Nulls.SKIP)
  public boolean isPersistent;

  public CreateAction() {
    isPersistent = false;
  }
}
