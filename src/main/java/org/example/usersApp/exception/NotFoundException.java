package org.example.usersApp.exception;

public class NotFoundException  extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
