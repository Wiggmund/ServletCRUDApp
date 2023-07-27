package org.example.usersApp.exception;

public class UserNotFoundException extends RuntimeException {
    private final static String MESSAGE = "User not found";
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
