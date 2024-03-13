package com.project.api.academia.exception;


public class ClienteUniqueViolationException extends RuntimeException {

    public ClienteUniqueViolationException(String message){
        super(message);
    }
}
