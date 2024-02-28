package ac.at.uibk.dps.nexa.lang.checker;

import exceptions.NexaException;


public class CheckerException extends NexaException {

  public CheckerException(Message message, Object... args) {
    super(String.format("%s (%d): ", message.error ? "Error" : "Warning", message.number)
        + String.format(
        message.message, args));
  }

  public enum Message {
    STATE_NAME_DOES_NOT_EXIST(0, "A state with the name '%s' does not exist", true),
    ILLEGAL_STATE_MACHINE_GRAPH(1, "The edge between states '%s' and '%s' is illegal", true),
    MULTIPLE_TRANSITIONS_WITH_SAME_EVENT(2, "State '%s' has multiple outwards transitions with the same event", true),
    STATE_MACHINE_HAS_NO_NAMED_ACTIONS(3, "State machine '%s' does not have any named actions", true),
    NAMED_ACTION_DOES_NOT_EXIST(4, "State machine '%s' does not have a named action with the name '%s'", true),
    ACTION_NAME_IS_NOT_UNIQUE(5, "Action name '%s' is not unique", true),
    STATE_NAME_IS_NOT_UNIQUE(6, "State name '%s' is not unique", true);

    public final int number;

    public final String message;

    public final boolean error;

    Message(int number, String message, boolean error) {
      this.number = number;
      this.message = message;
      this.error = error;
    }
  }
}
