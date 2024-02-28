package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.ActionClass.Type;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.AssignActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.CreateActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.InvokeActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.LockActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.MatchActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.RaiseActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.TimeoutActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.TimeoutResetActionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.actions.UnlockActionClass;
import java.util.Objects;
import java.util.Optional;

public abstract class Action {

  public final Optional<String> name;

  protected Action(Optional<String> name) {
    this.name = name;
  }

  /**
   * Build an action, given an action class.
   *
   * @param actionClass An action class describing the action to build.
   * @return The built action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(ActionClass actionClass) throws IllegalArgumentException {
    switch (actionClass.type) {
      case Type.Assign -> {
        return AssignAction.build((AssignActionClass) actionClass);
      }
      case Type.Create -> {
        return CreateAction.build((CreateActionClass) actionClass);
      }
      case Type.Invoke -> {
        return InvokeAction.build((InvokeActionClass) actionClass);
      }
      case Type.Lock -> {
        return LockAction.build((LockActionClass) actionClass);
      }
      case Type.Match -> {
        return MatchAction.build((MatchActionClass) actionClass);
      }
      case Type.Raise -> {
        return RaiseAction.build((RaiseActionClass) actionClass);
      }
      case Type.Timeout -> {
        return TimeoutAction.build((TimeoutActionClass) actionClass);
      }
      case Type.TimeoutReset -> {
        return TimeoutResetAction.build((TimeoutResetActionClass) actionClass);
      }
      case Type.Unlock -> {
        return UnlockAction.build((UnlockActionClass) actionClass);
      }
      default -> throw new IllegalStateException("Unexpected value: " + actionClass.type);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Action action = (Action) o;
    return Objects.equals(name, action.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
