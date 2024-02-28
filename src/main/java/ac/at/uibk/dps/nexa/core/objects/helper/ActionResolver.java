package ac.at.uibk.dps.nexa.core.objects.helper;

import ac.at.uibk.dps.nexa.core.objects.StateMachine;
import ac.at.uibk.dps.nexa.core.objects.actions.Action;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionOrActionReferenceClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionReferenceClass;

public class ActionResolver {

  private final StateMachine stateMachine;

  public ActionResolver(StateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }

  public Action resolve(ActionOrActionReferenceClass actionOrActionReferenceClass) throws IllegalStateException {
    switch (actionOrActionReferenceClass) {
      // An inline action is provided as an action class, since this action is inline it needs to be constructed
      case ActionClass actionClass -> {
        return Action.build(actionClass);
      }
      // An action reference is a reference to a named action contained within the state machine, we provide this action
      case ActionReferenceClass actionReferenceClass -> {
        return this.stateMachine.getActionByName(actionReferenceClass.reference);
      }
      default -> throw new IllegalStateException("Unexpected value: " + actionOrActionReferenceClass);
    }
  }
}
