package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {

                String sentence_from_client;
                String sentence_to_client;

                try(ServerSocket welcomeSocket = new ServerSocket(6543);
                    Socket connectionSocket = welcomeSocket.accept();
                    //Tạo input stream, nối tới Socket
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    //Tạo outputStream, nối tới socket
                    DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());){
                    while(true) {
                        sentence_from_client = inFromClient.readLine();
                        sentence_to_client = sentence_from_client +" (Server accepted!)" + '\n';
                        outToClient.writeBytes(sentence_to_client);
                    }

                } catch (SocketException socketException){
                    System.out.println(socketException.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


}
