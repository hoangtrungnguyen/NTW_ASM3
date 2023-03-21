package org.example.server;

import org.example.server.exception.InvalidUserNameException;
import org.example.server.model.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthService {

    boolean isLogin = false;

    User user;
    PrintWriter out;
    public AuthService(PrintWriter out){
        this.out = out;
    }

    DatabaseHelper databaseHelper = new DatabaseHelper();

    public void checkingUsername(String username){
        try {
            System.out.println("Server username: "+username);
            User user = databaseHelper.getUserByUsername(username);
            if(user == null){
                out.println(Response.responseFail("Not found user"));
                System.out.println("Sent message not foudn");
                return;
            }
            this.user = user;
            out.println(Response.responseOk("User found"));
        } catch (InvalidUserNameException exception){
            out.println("OKay");
        } catch (Exception exception){
            out.println("not okay");
        }
    }
    public void checkPassword(String password) {
        System.out.println("Server password: "+password);

        try{
            if(user.password.equals(password)){
                isLogin = true;
                out.println(Response.responseOk("Logged in success"));
            } else {
                out.println(Response.responseFail("Wrong password"));
            }
        } catch (Exception exception){
            out.println(Response.responseFail("Unexpected error"));
        }
    }

    public void logout() {
       if(user != null && isLogin){
           user = null;
           isLogin = false;
           out.println(Response.responseOk("Log out success"));
       } else {
           if(user != null){
               out.println(Response.responseFail("Current user session hasn't logged in yet"));
           } else {
               out.println(Response.responseFail("There is no current user"));
           }
       }
    }

    public void echo(String message) {
        out.println(Response.responseOk(message));
    }
}
