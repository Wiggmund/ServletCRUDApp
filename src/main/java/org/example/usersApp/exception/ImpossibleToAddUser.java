package org.example.usersApp.exception;

public class ImpossibleToAddUser  extends RuntimeException {
    public ImpossibleToAddUser(String message){
        super(message);
    }
}
