package org.example.client.model;

import org.example.server.Action;

public class ValidateUsernameRequest {

    public String uname;


    public ValidateUsernameRequest(String uname){
        this.uname = uname;
    }
    @Override
    public String toString() {
        return Action.USER.name()+",request:"+uname;
    }
}
