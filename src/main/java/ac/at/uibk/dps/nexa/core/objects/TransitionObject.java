package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;
import org.jgrapht.graph.DefaultEdge;

public class TransitionObject extends DefaultEdge implements CheckedObject {

  @Override
  public CheckedObject validate() throws CheckerException {
    return this;
  }
}
