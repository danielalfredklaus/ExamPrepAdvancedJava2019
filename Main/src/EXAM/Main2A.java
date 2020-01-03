package EXAM;

import Serialization_Sockets.Book;
import lombok.Cleanup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Main2A {
    public static void main(String[] args) throws Exception{
        int Port = 10000;
        ServerSocket ss = new ServerSocket(Port);

        while (true){
            Socket socket = ss.accept();
            new Thread(()-> handleClient(socket)).start();

    }




    }

    private static void handleClient(Socket socket){
        try {
            @Cleanup ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            @Cleanup ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String result = "";
            while (true) {
                if(ois.readObject().toString().equals("done"))break;
                result = result + " " + ois.readObject().toString();
                System.out.println(result);
            }
            oos.writeObject(result);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}
