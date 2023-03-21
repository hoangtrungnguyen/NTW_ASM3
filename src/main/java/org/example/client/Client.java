package org.example.client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket("localhost", 8080); // create client socket and connect to server
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        Service service = Service.getInstance(out,in);
        while (true) {

            if(!service.isValidUsername){
                System.out.print("Enter username: ");
                userInput = stdIn.readLine();
                if (userInput == null) {
                    continue; // exit loop if user enters null (e.g. by typing Ctrl+D)
                }

                service.validateUsername(userInput);


            } else if(!service.isLogin){
                System.out.print("Enter password: ");
                userInput = stdIn.readLine(); // read user input

                if (userInput == null) {
                    continue; // exit loop if user enters null (e.g. by typing Ctrl+D)
                }

                service.validatePassword(userInput);


            } else {
                // logged in

                System.out.print("Enter message (enter exit to stop): ");
                userInput = stdIn.readLine(); // read use

                if(userInput.equalsIgnoreCase("exit")){
                    boolean isStop = service.logout();
                    if(isStop) {
                        out.close();
                        in.close();
                        stdIn.close();
                        socket.close();
                        System.exit(1);
                        break;
                    }

                } else {
                    service.echo(userInput);
                }

            }


        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
