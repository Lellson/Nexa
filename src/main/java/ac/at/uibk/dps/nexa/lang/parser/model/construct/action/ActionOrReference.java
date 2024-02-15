package ac.at.uibk.dps.nexa.lang.model.construct.action;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(Action.class),
    @JsonSubTypes.Type(ActionReference.class)
})
public interface ActionOrReference {

}
