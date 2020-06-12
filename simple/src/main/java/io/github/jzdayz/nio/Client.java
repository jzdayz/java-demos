package io.github.jzdayz.nio;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket s = new Socket();
        s.connect(new InetSocketAddress("127.0.0.1",8081));
        byte[] a = new byte[1024];
        try (
                InputStream inputStream = s.getInputStream();
                ){
                    inputStream.read(a);
        }

        System.out.println(new String(a));


    }
}
