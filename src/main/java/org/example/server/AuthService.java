package org.example.server;

import org.example.server.exception.InvalidPasswordException;
import org.example.server.exception.InvalidUserNameException;
import org.example.server.model.User;

import java.io.DataOutputStream;
import java.io.IOException;

public class AuthService {

    boolean isLogin = false;

    String username;
    DataOutputStream out;
    public AuthService(DataOutputStream out){
        this.out = out;
    }

    DatabaseHelper databaseHelper = new DatabaseHelper();

    public void checkingUsername(String username){
        try {
            System.out.println("Server username: "+username);
            this.username = username;
            User user = databaseHelper.getUserByUsername(username);
            if(user == null){
                out.writeBytes("Not found user");
                System.out.println("Sent message not foudn");
                return;
            }
            out.writeBytes("OKay");
        } catch (InvalidUserNameException exception){
            try {
                out.writeBytes("OKay");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception exception){
            try {
                out.writeBytes("not okay");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean checkPassword(String password) {
        System.out.println("Server password: "+password);

        try{
            User user =  databaseHelper.getUserByUsername(this.username);

            if(user.password.equals(password)){
                isLogin = true;
                return true;
            } else {
                return false;
            }

        } catch (InvalidUserNameException exception){
//            helper.sendErrorResponse("Not found user");
        } catch (Exception exception){
//            helper.sendErrorResponse("Error");
        }

        return false;
    }

    public void logout() {

    }

    public void echo(String message) {

    }
}
