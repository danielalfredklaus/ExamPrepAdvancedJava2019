package Sockets.WithThreading;

import Serialization_Sockets.Book;
import lombok.Cleanup;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DownloaderClient {
    public static void main(String[] args) throws Exception{
        String HOST = "localhost";
        int PORT = 123456;
        @Cleanup var socket = new Socket(HOST,PORT);
        @Cleanup var oos = new ObjectOutputStream(socket.getOutputStream());
        @Cleanup var ois = new ObjectInputStream(socket.getInputStream());

        @SuppressWarnings("unchecked")
        var result = (HashMap<String, Book>)ois.readObject();

        System.out.println(result);
    }
}
