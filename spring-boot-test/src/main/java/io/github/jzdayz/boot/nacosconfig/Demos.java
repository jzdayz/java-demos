package io.github.jzdayz.boot.nacosconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Demos {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Demos.class, args);
        String a = context.getEnvironment().getProperty("mll.tag");
        System.out.println(a);
    }
}
