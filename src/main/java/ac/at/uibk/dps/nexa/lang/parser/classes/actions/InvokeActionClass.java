package ac.at.uibk.dps.nexa.lang.parser.classes.actions;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class InvokeActionClass extends ActionClass {

  @NotNull
  public String serviceTypeName;

  @NotNull
  public String value;

  @JsonSetter(nulls = Nulls.SKIP)
  public boolean isLocal = false;

  public Optional<Map<String, String>> input = Optional.empty();

  public Optional<String[]> done = Optional.empty();
}
