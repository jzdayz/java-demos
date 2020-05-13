package io.github.jzdayz.event;

import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ContextListener implements ApplicationListener<EnvironmentChangeEvent> {

    private Environment environment;


    public ContextListener(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        System.err.println(environment.getProperty("info.foo"));;
    }
}
