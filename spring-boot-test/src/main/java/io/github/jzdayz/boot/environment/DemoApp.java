package io.github.jzdayz.boot.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApp {

    @Value("${a:b}")
    private String a;

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(DemoApp.class, args);
    }
}
