package org.example.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

import java.net.*;
import java.io.*;

public class TestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080); // create server socket
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept(); // wait for client connection
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("From client: " + inputLine);
            if (inputLine.length() >= 5) { // check if input length is at least 5 characters
                out.println("Message received: " + inputLine);
            } else {
                out.println("Invalid");
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
