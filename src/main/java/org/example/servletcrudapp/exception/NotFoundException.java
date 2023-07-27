package org.example.servletcrudapp.exception;

public class NotFoundException  extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
