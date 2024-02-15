package ac.at.uibk.dps.nexa.lang.model.construct.action;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class InvokeAction extends Action {

  @NotNull
  public String serviceTypeName;

  @NotNull
  public String value;

  @JsonSetter(nulls = Nulls.SKIP)
  public boolean isLocal;

  public Optional<Map<String, String>> input;

  public Optional<String[]> done;

  public InvokeAction() {
    isLocal = false;
  }
}
