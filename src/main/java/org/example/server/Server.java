package org.example.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) {




                try(ServerSocket welcomeSocket = new ServerSocket(6543)){



                    while(true) {
                        try( Socket connectionSocket = welcomeSocket.accept();
                             BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                             DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream())) {
                            AuthService service = new AuthService(outToClient);

                            String sentence_to_client;
                            String sentence_from_client;
                            if ((sentence_from_client = inFromClient.readLine()) != null) { // listen for messages from the client
                                sentence_to_client = "Received message from client: " + sentence_from_client;
                                System.out.println(sentence_to_client);

                                String[] data = sentence_from_client.split(" ");

                                Action action = filterAction(data[0]);

                                if (action == null) {
                                    sentence_to_client = "Invalid action";
                                    outToClient.writeBytes(sentence_to_client);
                                    continue;
                                }
                                process(action, service, data[1]);
                            }

                        }

                    }

                } catch (SocketException socketException){
                    System.out.println(socketException.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                }
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
