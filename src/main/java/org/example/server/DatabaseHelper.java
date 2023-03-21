package org.example.server;

import org.example.server.exception.InvalidUserNameException;
import org.example.server.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class DatabaseHelper {
    static String user_file = "/user.txt";
    private static DatabaseHelper instance = null;


    public static synchronized DatabaseHelper getInstance()
    {
        if (instance == null)
            instance = new DatabaseHelper();

        return instance;
    }

    public User getUserByUsername(String username) throws InvalidUserNameException {

            List<User> users = getAllUser();

            boolean isExist = users.stream().anyMatch(user -> {
                return user.username.equalsIgnoreCase(username);
            });

            if (!isExist) return null;
            return users.stream().takeWhile(user -> user.username.equalsIgnoreCase(username)).findFirst().get();

    }


    private List<User> getAllUser(){
        List<User> users =  new ArrayList<>();
        // create a class instance to access the resource
        Class<DatabaseHelper> clazz = DatabaseHelper.class;

        InputStream inputStream = clazz.getResourceAsStream("/user.txt");
        if(inputStream ==null){
            System.out.println("can't found user.txt ");
            return users;
        }
        try(BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream))) {

            String line = reader.readLine();
            while (line != null) {
                String[] rows = line.split(",");
                users.add(new User(rows[0], rows[1], rows[2]));
                line = reader.readLine();
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }

//    public
}
