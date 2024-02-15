package ac.at.uibk.dps.nexa.lang.model.construct.helper;

import ac.at.uibk.dps.nexa.lang.model.construct.Guard;
import ac.at.uibk.dps.nexa.lang.model.construct.GuardReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A helper interface describing a type that can be a guard or a reference to a guard.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(Guard.class),
    @JsonSubTypes.Type(GuardReference.class)
})
public interface GuardOrReference {

}
