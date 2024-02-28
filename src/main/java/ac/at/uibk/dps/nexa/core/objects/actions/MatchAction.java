package ac.at.uibk.dps.nexa.core.objects.actions;

import ac.at.uibk.dps.nexa.lang.parser.classes.actions.MatchActionClass;
import java.util.Optional;

public class MatchAction extends Action {

  private MatchAction(Optional<String> name) {
    super(name);
  }

  /**
   * Build a match action, given a match action class.
   *
   * @param matchActionClass A match action class describing the match action to build.
   * @return The built match action if successful.
   * @throws IllegalArgumentException In case building failed, building can fail for various reasons.
   */
  public static Action build(MatchActionClass matchActionClass) throws IllegalArgumentException {
    var action = new MatchAction(matchActionClass.name);

    return action;
  }
}
