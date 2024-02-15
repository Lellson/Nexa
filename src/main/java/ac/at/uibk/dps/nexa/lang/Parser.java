package ac.at.uibk.dps.nexa.lang;

import ac.at.uibk.dps.nexa.lang.model.construct.CollaborativeStateMachine;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.util.stream.Collectors;

class BeanDeserializerWithValidation extends BeanDeserializer {

  private Validator validator;

  protected BeanDeserializerWithValidation(BeanDeserializerBase src) {
    super(src);

    // Create the validator
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Override
  public Object deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    // Call the base deserialization
    Object instance = super.deserialize(parser, context);

    // Check for violations, in case we find violations we throw an IllegalArgumentException that
    // is handled in the parse function below
    var violations = validator.validate(instance);
    if (!violations.isEmpty()) {
      throw new IllegalArgumentException(violations.stream()
          .map(
              v -> v.getMessage())
          .collect(Collectors.joining(",")));
    }

    return instance;
  }
}

class BeanDeserializerModifierWithValidation extends BeanDeserializerModifier {

  @Override
  public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config,
      BeanDescription description, JsonDeserializer<?> deserializer) {
    // Provide the deserializer with validation
    if (deserializer instanceof BeanDeserializer) {
      return new BeanDeserializerWithValidation((BeanDeserializer) deserializer);
    }

    return deserializer;
  }
}

public class Parser {

  public record Options() {

  }

  private Options options;

  private ObjectMapper mapper;

  public Parser(Options options) {
    this.options = options;

    // Add a deserialization module that adds bean validation
    SimpleModule validationModule = new SimpleModule();
    validationModule.setDeserializerModifier(new BeanDeserializerModifierWithValidation());

    // Construct the mapper
    mapper = JsonMapper.builder()
        .addModule(new ParameterNamesModule())
        .addModule(new Jdk8Module())
        .addModule(validationModule)
        .enable(JsonParser.Feature.ALLOW_COMMENTS)
        .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
        .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
        .build();
  }

  public CollaborativeStateMachine parse(String json) throws LanguageException {
    try {
      return mapper.readValue(json, CollaborativeStateMachine.class);
    } catch (JsonProcessingException | IllegalArgumentException e) {
      throw new LanguageException(
          String.format("Parsing error: %s", e.getMessage()));
    }
  }
}
