package Serialization_Sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class BookClientExample {
    public static void main(String[] args) throws Exception {
        String HOST = "localhost";
        int PORT = 12345;
        Socket socket = new Socket(HOST, PORT);
        //The next two need to be in reverse order than the server
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        var books = (List<Book>)ois.readObject();
        System.out.println(books);

        Scanner scanner = new Scanner(System.in);

        while (true){
            String title = scanner.nextLine();
            if (title.isEmpty()) break;
            int year = Integer.parseInt(scanner.nextLine());

            oos.writeBoolean(true);
            oos.writeObject(new Book(title, year));
        }


        scanner.close();
        socket.close();
    }
}
