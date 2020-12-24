package io.github.jzdayz.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Demo2 {
    public static void main(String[] args) {
        System.setProperty("server.port","50505");
        SpringApplication.run(Demo2.class, args);
    }

    @GetMapping("test")
    public Object test(){
        return "hello2";
    }

    @GetMapping("ping")
    public Object test1(){
        return "PONG";
    }
}
