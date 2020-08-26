package io.github.jzdayz.boot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@AllArgsConstructor
public class Tests {

    public static void main(String[] args) {
        SpringApplication.run(Tests.class, args);
    }

}
