package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.core.objects.helper.ActionResolver;
import ac.at.uibk.dps.nexa.lang.parser.classes.OnTransitionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.TransitionClass;

public class OnTransition extends Transition {

  public final String event;

  public OnTransition(TransitionClass transitionClass, ActionResolver actionResolver) {
    super(transitionClass, actionResolver);

    var onTransitionClass = (OnTransitionClass) transitionClass;

    this.event = onTransitionClass.event;
  }
}
