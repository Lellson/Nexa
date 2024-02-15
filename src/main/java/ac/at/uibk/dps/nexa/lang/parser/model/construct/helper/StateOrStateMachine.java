package ac.at.uibk.dps.nexa.lang.model.construct.helper;

import ac.at.uibk.dps.nexa.lang.model.construct.State;
import ac.at.uibk.dps.nexa.lang.model.construct.StateMachine;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A helper interface describing a type that can be a state or a state machine.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(State.class),
    @JsonSubTypes.Type(StateMachine.class)
})
public interface StateOrStateMachine {

}
