package ac.at.uibk.dps.nexa.core.objects;

import java.util.function.Supplier;
import org.jgrapht.graph.SimpleGraph;

public class StateMachineObject extends SimpleGraph<StateObject, TransitionObject> {

  public StateMachineObject(Class<? extends TransitionObject> edgeClass) {
    super(edgeClass);
  }

  public StateMachineObject(Supplier<StateObject> vertexSupplier,
      Supplier<TransitionObject> edgeSupplier, boolean weighted) {
    super(vertexSupplier, edgeSupplier, weighted);
  }
}
