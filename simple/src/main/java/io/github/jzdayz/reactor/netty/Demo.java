package io.github.jzdayz.reactor.netty;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

@Slf4j
public class Demo {
    
    public static void main(String[] args) {
        DisposableServer server = HttpServer.create().host("0.0.0.0").port(9991).route(routes -> routes
                .get("/hello", (request, response) -> response.sendString(Mono.just("Hello World!")))
                .post("/echo", (request, response) -> response.send(request.receive().retain()))
                .get("/path/{param}", (request, response) -> response.sendString(Mono.just(request.param("param"))))
                .ws("/ws", (wsInbound, wsOutbound) -> wsOutbound.sendString(wsInbound.receive().retain().asString()))).bindNow();
        server.onDispose().block();
    }
    
}
