package org.example.client.model;

public class EchoRequest {

    private final String message;


    public EchoRequest(String message){
        this.message = message;
    }
    @Override
    public String toString() {
        return Action.ECHO.name()+",request:"+message;
    }
}
