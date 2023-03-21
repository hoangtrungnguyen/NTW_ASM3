package org.example.test;

import java.io.*;
import java.net.*;

public class TestClient {
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

        while (true) {
            System.out.print("Enter message: ");
            userInput = stdIn.readLine(); // read user input
            if (userInput == null) {
                break; // exit loop if user enters null (e.g. by typing Ctrl+D)
            }
            out.println(userInput); // send user input to server
            System.out.println("Server response: " + in.readLine()); // print server response
        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
