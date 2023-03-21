package org.example.server;

public class Response {

    static public String responseOk(String message){
        return Action.OK.name()+ ",response:"+message;
    }

    static public String responseFail(String message){
        return Action.ERR.name() +",response:"+message;
    }
}
