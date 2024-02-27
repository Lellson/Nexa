package ac.at.uibk.dps.nexa.core.io;

import ac.at.uibk.dps.nexa.core.CoreException;
import ac.at.uibk.dps.nexa.core.objects.StateMachineObject;
import ac.at.uibk.dps.nexa.core.objects.StateObject;
import java.io.Writer;
import org.jgrapht.nio.dot.DOTExporter;

public class StateMachineObjectDotExporter {

  public static void export(Writer out, StateMachineObject stateMachineObject)
      throws CoreException {
    try {
      var exporter = new DOTExporter((vertex) -> {
        if (vertex instanceof StateObject == false) {
          throw new IllegalArgumentException(
              "expected a state object as the state machine graph vertex");
        }

        var state = (StateObject) vertex;
        return state.getName();
      });

      exporter.exportGraph(stateMachineObject, out);
    } catch (IllegalArgumentException e) {
      throw new CoreException(
          String.format("Unexpected error while exporting a state machine object to DOT",
              e.getMessage()));
    }
  }
}
