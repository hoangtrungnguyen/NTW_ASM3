package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

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

            String[] data = inputLine.split(" ");
            try {
                Action action = filterAction(data[0]);

                if (action == null) {
                      out.println("isOk:false,message:invalid action");
                } else {
                    process(action, new AuthService(out), data[1]);
                }

            }catch (IndexOutOfBoundsException exception){
                out.println(Response.responseFail("Request is not match the format"));
            }

        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }


            public static void process(Action action, AuthService service,String data){
                switch (action){
                    case USER:
                        service.checkingUsername(data);
                        break;
                    case PASS:
                        service.checkPassword(data);
                        break;
                    case ECHO:
                        service.echo(data);
                        break;
                    case LOGOUT:
                        service.logout();
                        break;
                }
            }

            public static Action filterAction(String action) {
               try{
                   return Action.valueOf(action);
               } catch (Exception ex){
                   return null;
               }
            }

}
