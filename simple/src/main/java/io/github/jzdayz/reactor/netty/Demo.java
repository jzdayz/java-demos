package io.github.jzdayz.reactor.netty;

import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.server.HttpServer;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo {

    public static void main(String[] args) {
//        httpServer();
//        tcpClient();
        httpClient();
    }

    private static void httpClient() {
        HttpClient client = HttpClient.create().port(80).baseUrl("http://baidu.com");
        client.get()
                .uri("/")
                .responseContent()
                .log()
                .blockLast();
    }

    private static void tcpClient() {
        Connection connection = TcpClient.create().host("baidu.com").port(80)
                .doOnConnected(conn -> conn.addHandler(new ReadTimeoutHandler(10, TimeUnit.SECONDS)))
                .handle((in, out) -> {
                    in.receive().asString().log().doOnEach(e -> System.out.println(e.get()));
                    return Mono.empty();
                })
                .connectNow();
        connection.onDispose().block();
    }

    private static void httpServer() {
        DisposableServer server = HttpServer.create().host("0.0.0.0").port(9991).route(routes -> routes
                .get("/hello", (request, response) -> response.sendString(Mono.just("Hello World!")))
                .post("/echo", (request, response) -> response.send(request.receive().retain()))
                .get("/path/{param}", (request, response) -> response.sendString(Mono.just(request.param("param"))))
                .ws("/ws", (wsInbound, wsOutbound) -> wsOutbound.sendString(wsInbound.receive().retain().asString())))
                .bindNow();
        server.onDispose().block();
    }

}
