package ac.at.uibk.dps.nexa.lang.model.construct.action;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class RaiseAction extends Action {

  @NotNull
  public String event;

  @NotNull
  public Channel channel;

  public Optional<Map<String, String>> data;
}
