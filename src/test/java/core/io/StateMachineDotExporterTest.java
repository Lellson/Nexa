package core.io;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import ac.at.uibk.dps.nexa.core.io.StateMachineObjectDotExporter;
import ac.at.uibk.dps.nexa.lang.checker.Checker;
import ac.at.uibk.dps.nexa.lang.parser.Parser;
import data.DefaultDescriptions;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;

public class StateMachineDotExporterTest {

  @Test
  public void testExportPositive() {
    var json = DefaultDescriptions.complete;

    var parser = new Parser(new Parser.Options());
    assertDoesNotThrow(() -> {
      var out = new StringWriter();
      StateMachineObjectDotExporter.export(out,
          new Checker(new Checker.Options()).check(parser.parse(json))
              .getStateMachineObjectByName("Name").get());

      System.out.println(out.toString());
    });
  }
}
