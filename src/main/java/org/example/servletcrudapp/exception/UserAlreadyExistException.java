package org.example.servletcrudapp.exception;

public class UserAlreadyExistException extends RuntimeException {
    private static final String MESSAGE = "That user already exist";

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException() {
        super(MESSAGE);
    }
}
