package org.example.client.model;

import org.example.server.Action;

public class ValidatePasswordRequest {

    String password="";

    String username="";

    public ValidatePasswordRequest(String password, String username){
        this.password = password;
        this.username = username;
    }


    @Override
    public String toString() {
        return Action.PASS.name()+",request:"+username+"-"+password;
    }
}
