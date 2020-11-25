package io.github.jzdayz.boot.binder;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(TestApp.A.class)
public class TestApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(TestApp.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);

        try (
                ConfigurableApplicationContext run = springApplication.run(args)
        ) {
            A a = run.getBean(A.class);
            Binder.get(run.getEnvironment()).bind("my.test", Bindable.ofInstance(a));
            System.out.println(a);
        }
    }

    @Data
    @ConfigurationProperties("my.test1")
    public static class A {
        private String name;
        private Integer age;
    }
}
