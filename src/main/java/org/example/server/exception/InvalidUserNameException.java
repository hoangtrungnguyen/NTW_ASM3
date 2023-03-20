package org.example.server.exception;

public class InvalidUserNameException extends  AuthException {
    public InvalidUserNameException(){
        super("Invalid email");
    }
}

