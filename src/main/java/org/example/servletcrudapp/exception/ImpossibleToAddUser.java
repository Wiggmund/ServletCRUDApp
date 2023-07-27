package org.example.servletcrudapp.exception;

public class ImpossibleToAddUser  extends RuntimeException {
    public ImpossibleToAddUser(String message){
        super(message);
    }
}
