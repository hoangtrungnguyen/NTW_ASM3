package org.example.server.exception;

public abstract class AuthException extends  Exception{

    public AuthException(String message ){
        super(message);
    }
}
