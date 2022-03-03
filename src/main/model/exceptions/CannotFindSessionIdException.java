package model.exceptions;

public class CannotFindSessionIdException extends Exception {

    public CannotFindSessionIdException() {
        super("Session ID was not found in the database!");
    }
}
