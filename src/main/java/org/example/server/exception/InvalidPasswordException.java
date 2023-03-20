package org.example.server.exception;

public class InvalidPasswordException  extends  AuthException{

    public InvalidPasswordException(){
        super("Invalid password");
    }
}
