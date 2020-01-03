package Sockets.WithThreading;

import Serialization_Sockets.Book;
import lombok.Cleanup;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

//Sockets with Threading
public class Server {
    public static void main(String[] args) throws Exception {
        int PORT = 12345;

        var books = new HashMap<String, Book>();
        var clients = new HashMap<Socket, Thread>();

        //Dispatcher Thread #1
        BiConsumer<Integer, BiConsumer<Socket, HashMap<String, Book>>> makeDispatcher =
                (p, handler) ->
                        new Thread(() -> {
                            try {
                                @Cleanup var ss = new ServerSocket(p);
                                while (true) {
                                    Socket socket = ss.accept();
                                    Thread t = new Thread(() -> handler.accept(socket, books));
                                    t.start();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }).start();

        makeDispatcher.accept(PORT, Server::handleUploaderClient);
        makeDispatcher.accept(PORT, Server::handleDownloaderClient);



       /* //Dispatcher Thread #2 Not needed since dispatcher 1 handles both
        new Thread(()-> {
            try {
                @Cleanup var ss = new ServerSocket(PORT);
                while (true){
                    Socket socket = ss.accept();
                    Thread t = new Thread(()-> handleDownloaderClient(socket, books));
                    t.start();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }).start();*/
    }

    private static void handleUploaderClient(Socket socket, HashMap<String, Book> books){
        try {
            @Cleanup var forCleanup = socket;
            @Cleanup var ois = new ObjectInputStream(socket.getInputStream());
            @Cleanup var oos = new ObjectOutputStream(socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void handleDownloaderClient(Socket socket, HashMap<String, Book> books){
        try {
            @Cleanup var forCleanup = socket;
            @Cleanup var ois = new ObjectInputStream(socket.getInputStream());
            @Cleanup var oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(books);
            oos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
