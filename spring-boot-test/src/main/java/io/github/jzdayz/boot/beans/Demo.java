package io.github.jzdayz.boot.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.List;

@SpringBootApplication
public class Demo {
    public static void main(String[] args) {
        try (
                ConfigurableApplicationContext context = SpringApplication.run(Demo.class, args)
                ){

        }
    }

    @Data
    @AllArgsConstructor
    public static class A {
        private String name;
    }

    @Order(2)
    @Bean
    public A a1(){
        return new A("a1");
    }
    @Order(1)
    @Bean
    public A a2(){
        return new A("a2");
    }


}

