package model.exceptions;

// exception class for session id
public class CannotFindSessionIdException extends Exception {
    // EFFECTS: sends an exception if session id was not found in data file
    public CannotFindSessionIdException() {
        super("Session ID was not found in the database!");
    }
}
