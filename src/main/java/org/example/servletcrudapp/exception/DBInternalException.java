package org.example.servletcrudapp.exception;

public class DBInternalException extends RuntimeException {
    private static final String MESSAGE = "Database internal error has occurred";

    public DBInternalException(String message) {
        super(message);
    }

    public DBInternalException() {
        super(MESSAGE);
    }
}
