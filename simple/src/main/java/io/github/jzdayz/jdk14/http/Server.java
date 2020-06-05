package io.github.jzdayz.jdk14.http;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws Exception{
        HttpServer httpServer = HttpServer.create();
        httpServer.bind(InetSocketAddress.createUnresolved("localhost",9898),0);
//        httpServer.createContext("/", HttpHandler)
        httpServer.start();
    }
}
