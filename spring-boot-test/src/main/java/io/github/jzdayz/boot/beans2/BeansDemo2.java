package io.github.jzdayz.boot.beans2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BeansDemo2 {

    @Autowired
    private List<A> as;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BeansDemo2.class, args);
        List<A> as = context.getBean(BeansDemo2.class).as;
        System.out.println(as);
    }

    @Bean
    public A a1(){
        return new A("a1");
    }

    @Bean
    public A a2(){
        return new A("a2");
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class A{
        private String name;
    }

}
