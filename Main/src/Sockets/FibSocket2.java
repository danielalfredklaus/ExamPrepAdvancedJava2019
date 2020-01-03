package Sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import lombok.Cleanup;

public class FibSocket2 {
    public static void main(String[] args) throws Exception {
        int PORT = 12345;
        @Cleanup var ss = new ServerSocket(PORT);
        while (true){
            @Cleanup Socket s = ss.accept();
            //new Thread(()->handleClient(s)).start(); triggers Unhandled Exception
            new Thread(()-> {
                try {
                    handleClient(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }

    private static void handleClient(Socket s) throws IOException {
        try {
            @Cleanup var sc = new Scanner(s.getInputStream());
            @Cleanup var pw = new PrintWriter(s.getOutputStream());

            while (sc.hasNextInt()) {
                var n = sc.nextInt();
                var result = fibSimpleRecursive(n);

                pw.println(result);
                pw.flush();
            }
        } catch (Exception e) {
            //wont happen
            e.printStackTrace();
        }

    }

    public static int fibSimpleRecursive(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        return fibSimpleRecursive(n-1) + fibSimpleRecursive(n-2);
    }


}
