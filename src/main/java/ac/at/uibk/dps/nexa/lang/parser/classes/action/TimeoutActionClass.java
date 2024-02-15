package ac.at.uibk.dps.nexa.lang.parser.classes.action;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class TimeoutActionClass extends ActionClass {

  @NotNull
  public String delay;

  @NotNull
  public ActionOrReference[] actions;
}
