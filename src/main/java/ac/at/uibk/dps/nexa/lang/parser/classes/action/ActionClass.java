package ac.at.uibk.dps.nexa.lang.parser.classes.action;

import ac.at.uibk.dps.nexa.lang.parser.classes.Construct;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@JsonDeserialize(using = ActionDeserializer.class)
public class ActionClass extends Construct implements ActionOrReference {

  public enum Type {
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

  @NotNull
  public Type type;

  public Optional<String> name;
}

class ActionDeserializer extends JsonDeserializer<ActionClass> {

  @Override
  public ActionClass deserialize(JsonParser parser, DeserializationContext context)
      throws IOException, JsonProcessingException {
    var codec = parser.getCodec();
    var treeNode = parser.readValueAsTree();

    // Try to determine the action type that belongs to the type property.
    var typeClasses = Map.of(
        "assign", AssignActionClass.class,
        "create", CreateActionClass.class,
        "invoke", InvokeActionClass.class,
        "lock", LockActionClass.class,
        "match", MatchActionClass.class,
        "raise", RaiseActionClass.class,
        "timeout", TimeoutActionClass.class,
        "timeoutReset", TimeoutResetActionClass.class
    );

    var type = codec.treeToValue(treeNode.get("type"), String.class);
    if (!typeClasses.containsKey(type)) {
      throw new IllegalArgumentException(String.format("Action type '%s' is not known", type));
    }

    // Return the appropriate action type. Action types are children of Action and would normally
    // invoke this deserializer. Therefore, the deserializer on action types should be set to none
    // for this to not result in an infinite recursion
    return parser.getCodec().treeToValue(treeNode, typeClasses.get(type));
  }
}