import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ac.at.uibk.dps.nexa.lang.parser.Parser;
import ac.at.uibk.dps.nexa.lang.parser.Parser.Options;
import ac.at.uibk.dps.nexa.lang.parser.ParserException;
import org.junit.jupiter.api.Test;


public class JsonParserTest {

  @Test
  public void testDescriptionPositive() {
    var json = """
        {
          "name": "Name",
          // Supports comments
          'version': '0.1', // Supports single quotes
          memoryMode: "distributed", // Supports unquoted keys
          "stateMachines": [
            {
              "name": "Name",
              "states": [
                {
                  "name": "Name",
                  "entry": [
                    {
                      "type": "assign",
                      "variable": "Variable",
                      "value": "Value"
                    }
                  ],
                  "exit": [],
                  "while": [],
                  "after": [],
                  "localContext": {},
                  "persistentContext": {},
                  "staticContext": {}
                }
              ],
              "localContext": {},
              "persistentContext": {},
              "guards": [],
              "actions": []
            }
          ],
          "localContext": {},
          "persistentContext": {}
        }
        """;

    var parser = new Parser(new Options());
    assertDoesNotThrow(() -> {
      var csm = parser.parse(json);
    });
  }

  @Test
  public void testDescriptionNegative() {
    var json = "{}" ;

    var parser = new Parser(new Options());
    assertThrows(ParserException.class, () -> {
      var csm = parser.parse(json);
      System.out.println(csm);
    });
  }
}
