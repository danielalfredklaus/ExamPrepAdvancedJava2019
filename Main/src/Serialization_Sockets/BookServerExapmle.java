package Serialization_Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;


public class BookServerExapmle {
    public static void main(String[] args) throws Exception {
        int Port = 12345;

        HashMap bookMap = new HashMap<String, Book>();
        ServerSocket ss = new ServerSocket(Port);
        while (true){
            Socket socket = ss.accept();
            new Thread(()-> handleUploaderClient(socket, bookMap)).start();
        }

    }

    private static void handleUploaderClient(Socket socket, HashMap<String, Book> bookMap)  {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                boolean clientSendsMoreBooks = ois.readBoolean();
                if (!clientSendsMoreBooks) break;

                Book book = (Book) ois.readObject();
                bookMap.put(book.getTitle(), book);
                System.out.println(bookMap);

            }
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

    private static void handleDownloaderClient(Socket socket, HashMap<String, Book> bookMap){


    }
}
