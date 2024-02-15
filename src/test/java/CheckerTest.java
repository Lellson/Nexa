import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import ac.at.uibk.dps.nexa.lang.checker.Checker;
import ac.at.uibk.dps.nexa.lang.parser.Parser;
import org.junit.jupiter.api.Test;

public class CheckerTest {

  @Test
  public void testCheckerPositive() {
    var json = """
        {
          name: 'Name',
          version: '0.1',
          memoryMode: "distributed",
          "stateMachines": [
            {
              name: 'Name',
              states: [
                {
                  name: 'Name',
                  entry: [
                    {
                      type: 'assign',
                      variable: 'Variable',
                      value: 'Value'
                    }
                  ],
                  exit: [],
                  while: [],
                  after: [],
                  localContext: {},
                  persistentContext: {},
                  staticContext: {}
                }
              ],
              localContext: {},
              persistentContext: {},
              guards: [],
              actions: []
            }
          ],
          localContext: {},
          persistentContext: {}
        }
        """;

    var parser = new Parser(new Parser.Options());
    assertDoesNotThrow(() -> {
      var csm = parser.parse(json);

      var checker = new Checker(new Checker.Options());
      checker.check(csm);
    });
  }
}
