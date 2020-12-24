package io.github.jzdayz.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class Demo1 {
    public static void main(String[] args) {
        System.setProperty("server.port", "50506");
        SpringApplication.run(Demo1.class, args);
    }

    @GetMapping("test")
    public Object test() {
        return "hello1";
    }

    @GetMapping("ping")
    public Object test1(){
        return "PONG";
    }
}