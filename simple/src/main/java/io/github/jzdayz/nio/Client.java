package io.github.jzdayz.nio;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket();
        s.connect(new InetSocketAddress("127.0.0.1", 8081));
        try (
                OutputStream outputStream = s.getOutputStream();
        ) {
            outputStream.write("A".getBytes());
            System.out.println("write A");
            outputStream.flush();
            new Scanner(System.in).next();
            outputStream.write("B".getBytes());
            System.out.println("write B");
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        s.close();

    }

}
