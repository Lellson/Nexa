package ac.at.uibk.dps.nexa.lang.checker;

import ac.at.uibk.dps.nexa.lang.parser.ParserException;
import ac.at.uibk.dps.nexa.lang.parser.classes.CollaborativeStateMachineClass;

public class Checker {

  public record Options() {

  }

  private Options options;

  public Checker(Options options) {
    this.options = options;
  }

  public CollaborativeStateMachineClass check() throws ParserException, CheckerException {
    return null;
  }
}
