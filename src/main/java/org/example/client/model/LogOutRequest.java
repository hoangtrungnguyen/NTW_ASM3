package org.example.client.model;

import org.example.server.Action;

public class LogOutRequest {
    @Override
    public String toString() {
        return Action.LOGOUT.name()+",request:e";
    }
}
