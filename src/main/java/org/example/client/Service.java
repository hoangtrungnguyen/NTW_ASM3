package org.example.client;

import org.example.client.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Service {

    private static  Service instance;

    boolean isLogin = false;

    boolean isValidUsername = false;


    static String uname = "";
    private PrintWriter out = null;

    private BufferedReader in = null;



    private Service(PrintWriter out,BufferedReader in ){
        this.out = out;
        this.in = in;
    }

    static public Service getInstance(PrintWriter out,BufferedReader in ){
        if (instance == null) {
            instance = new Service(out,in);
        }
        return instance;
    }


    public void validateUsername(String username) throws IOException {
        ValidateUsernameRequest request = new ValidateUsernameRequest(username);
        out.println(request.toString());
        String response = in.readLine();
        String[] rawData = response.split(",");
        if(rawData[0].equalsIgnoreCase(Action.OK.name())){
            isValidUsername = true;
            Service.uname = username;
            System.out.println("Username is correct");
        } else {
            System.out.println("Username is not correct please re-enter username");
        }
    }

    public void validatePassword(String password) throws IOException {
        ValidatePasswordRequest request = new ValidatePasswordRequest(password, Service.uname);
        out.println(request);
        String response = in.readLine();
        String[] rawData = response.split(",");
        if(rawData[0].equalsIgnoreCase(Action.OK.name())){
            isLogin = true;
            System.out.println("Login successful!!");
        } else {
            System.out.println("Password is not correct");
        }
    }



    public boolean logout() throws IOException {
        out.println(new LogOutRequest());
        String response = in.readLine();
        System.out.println(response);

        if(response.contains("ERR")){
            return false;
        } else {
            System.out.println("Log out successful!!");
            return true;
        }
    }

    public void echo(String message) throws IOException {
        out.println(new EchoRequest(message));
        String response = in.readLine();
        System.out.println("Reply from server: "+response);
    }

}
