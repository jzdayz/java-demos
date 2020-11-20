package io.github.jzdayz.boot.ioc;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Tests {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Tests.class, args);
        A bean = context.getBean(A.class);

    }

    @Component
    @AllArgsConstructor
    public static class A {

        private FactoryBean<Tests> tests;

        @PostConstruct
        public void init (){
            System.out.println(1);
        }
    }

}
