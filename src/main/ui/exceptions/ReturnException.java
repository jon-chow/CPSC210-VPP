package ui.exceptions;

// exception class for pressing the return button
public class ReturnException extends Exception {
    // EFFECTS: sends an exception if return button was pressed
    public ReturnException() {
        super("Return button was pressed!");
    }
}
