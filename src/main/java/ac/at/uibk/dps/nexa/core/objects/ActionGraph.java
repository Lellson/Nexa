package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.core.objects.actions.Action;
import java.util.Objects;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class ActionGraph extends SimpleDirectedGraph<Action, DefaultEdge> {

  private ActionGraph() {
    super(DefaultEdge.class);
  }

  public static ActionGraph build(Action[] actions)
      throws IllegalArgumentException {
    Objects.requireNonNull(actions);

    // Create this action graph
    var actionGraph = new ActionGraph();

    return actionGraph;
  }
}
