package ac.at.uibk.dps.nexa.lang.model.construct.helper;

import ac.at.uibk.dps.nexa.lang.model.construct.Guard;
import ac.at.uibk.dps.nexa.lang.model.construct.GuardReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(Guard.class),
    @JsonSubTypes.Type(GuardReference.class)
})
public interface GuardOrReference {

}
