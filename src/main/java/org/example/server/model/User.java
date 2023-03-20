package org.example.server.model;

public class User {
    public String username;
    public String uid;
    public String password;

    public User( String uid,String username, String password) {
        this.username = username;
        this.uid = uid;
        this.password = password;
    }
}
