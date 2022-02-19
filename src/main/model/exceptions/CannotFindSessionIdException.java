package model.exceptions;

public class CannotFindSessionIdException extends Exception {

    public CannotFindSessionIdException(String msg) {
        System.err.println(msg);
    }
}
