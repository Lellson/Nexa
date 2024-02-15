package ac.at.uibk.dps.nexa.lang.parser.classes.helper;

import ac.at.uibk.dps.nexa.lang.parser.classes.StateClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateMachineClass;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A helper interface describing a type that can be a state or a state machine.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(StateClass.class),
    @JsonSubTypes.Type(StateMachineClass.class)
})
public interface StateOrStateMachine {

}
