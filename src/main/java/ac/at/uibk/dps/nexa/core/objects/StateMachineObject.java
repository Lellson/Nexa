package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import ac.at.uibk.dps.nexa.lang.parser.classes.StateMachineClass;
import java.util.function.Supplier;
import org.jgrapht.graph.SimpleGraph;

public class StateMachineObject extends SimpleGraph<StateObject, TransitionObject> implements
    CheckedObject {

  public StateMachineObject(Class<? extends TransitionObject> edgeClass) {
    super(edgeClass);
  }

  public StateMachineObject(Supplier<StateObject> vertexSupplier,
      Supplier<TransitionObject> edgeSupplier, boolean weighted) {
    super(vertexSupplier, edgeSupplier, weighted);
  }

  @Override
  public CheckedObject validate() throws CheckerException {
    return this;
  }

  public static StateMachineObject build(StateMachineClass stateMachineClass) {
    StateMachineObject stateMachineObject = new StateMachineObject(TransitionObject.class);

    return stateMachineObject;
  }
}
