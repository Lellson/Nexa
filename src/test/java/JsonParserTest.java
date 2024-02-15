import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ac.at.uibk.dps.nexa.lang.LanguageException;
import ac.at.uibk.dps.nexa.lang.Parser;
import ac.at.uibk.dps.nexa.lang.Parser.Options;
import org.junit.jupiter.api.Test;


public class JsonParserTest {

  @Test
  public void testDescriptionPositive() {
    var json = """
        {
          "name": "Name",
          "version": "e2e9c29b-867a-412e-81fb-2e0eda56d69a",
          "memoryMode": "distributed",
          "stateMachines": [
            {
              "name": "Name",
              "states": [
                {
                  "name": "Name",
                  "description": "Description",
                  "entry": [
                    {
                      "name": "Name",
                      "description": "Description",
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
          "description": "Description",
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
    assertThrows(LanguageException.class, () -> {
      var csm = parser.parse(json);
      System.out.println(csm);
    });
  }
}
