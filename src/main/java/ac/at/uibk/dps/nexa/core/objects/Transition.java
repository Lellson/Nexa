package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.core.objects.helper.ActionResolver;
import ac.at.uibk.dps.nexa.lang.parser.classes.TransitionClass;
import org.jgrapht.graph.DefaultEdge;

public class Transition extends DefaultEdge {

  public final String target;

  public Transition(TransitionClass transitionClass, ActionResolver actionResolver) {
    this.target = transitionClass.target;
  }
}
