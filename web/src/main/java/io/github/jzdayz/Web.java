package io.github.jzdayz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Web {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Web.class);
        Environment bean = run.getBean(Environment.class);
        String a = bean.getProperty("a");
        System.out.println(a);
    }
}
