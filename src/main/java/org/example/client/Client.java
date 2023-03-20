package org.example.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    public static void main(String[] args) {
        try(Socket socket =  new Socket("127.0.0.1", 6543);
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String sentence_to_server;
            String sentence_from_server;

            //Lấy chuỗi ký tự nhập từ bàn phím
            while(true){
                System.out.print("Input from org.example.client: \n");
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                sentence_to_server = inFromUser.readLine();
                outToServer.writeBytes(sentence_to_server +"\n" );
                String message;
                message = inFromServer.readLine();
                System.out.println("FROM SERVER: " + message);

            }

        } catch (SocketException socketException){
            System.out.println(socketException.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
