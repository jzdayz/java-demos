package io.github.jzdayz.boot.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.GenericApplicationListenerAdapter;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@SpringBootApplication
public class App {



    public static class MyApplicationEvent extends ApplicationEvent{

        public MyApplicationEvent(Object source) {
            super(source);
        }

    }

    @Service
    public static class MyApplicationEventLis implements ApplicationListener<MyApplicationEvent> {

        @Override
        public void onApplicationEvent(MyApplicationEvent event) {
            System.out.println(event);
        }
    }

    public static void main(String[] args) {
        new GenericApplicationListenerAdapter(new MyApplicationEventLis());
//        SpringApplication springApplication = new SpringApplication(App.class);
//        springApplication.setWebApplicationType(WebApplicationType.NONE);
//
//        try (
//                ConfigurableApplicationContext run = springApplication.run(args)
//                ){
//
//            MyApplicationEvent m = new MyApplicationEvent("1");
//            run.publishEvent(m);
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
    }
}
