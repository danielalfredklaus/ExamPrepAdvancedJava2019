package Sockets;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FibSocket {
    public static void main(String[] args) throws Exception {
        int PORT = 12345;
        var ss = new ServerSocket(PORT);
        Socket s = ss.accept();
        var sc = new Scanner(s.getInputStream());
        var pw = new PrintWriter(s.getOutputStream());

        while (sc.hasNextInt()) {

            var n = sc.nextInt();
            var result = fibSimpleRecursive(n);

            pw.println(result);
            pw.flush();
        }


    }


    public static int fibSimpleRecursive(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        return fibSimpleRecursive(n-1) + fibSimpleRecursive(n-2);
    }
}
