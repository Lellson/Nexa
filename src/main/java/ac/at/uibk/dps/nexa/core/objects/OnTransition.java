package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.parser.classes.OnTransitionClass;
import ac.at.uibk.dps.nexa.lang.parser.classes.TransitionClass;

public class OnTransition extends Transition {

  public final String event;

  public OnTransition(TransitionClass transitionClass) {
    super(transitionClass);

    var onTransitionClass = (OnTransitionClass) transitionClass;
    
    this.event = onTransitionClass.event;
  }
}
