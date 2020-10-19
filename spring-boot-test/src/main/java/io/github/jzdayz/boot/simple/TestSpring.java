package io.github.jzdayz.boot.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SuppressWarnings("SpringFacetCodeInspection")
@SpringBootApplication
public class TestSpring {

    public static void main(String[] args) {
        SpringApplication.run(TestSpring.class,args);
    }

    @PreDestroy
    public void close(){
        System.err.println("close");
    }

}
