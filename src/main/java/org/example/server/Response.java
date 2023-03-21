package org.example.server;

public class Response {

    static public String responseOk(String message){
        return "isOk:true,response:"+message;
    }

    static public String responseFail(String message){
        return "isOk:false,response:"+message;
    }
}
