package io.github.jzdayz.boot.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableAsync
@SpringBootApplication
public class TestAsync {
    public static void main(String[] args) {
        try (
                ConfigurableApplicationContext context = SpringApplication.run(TestAsync.class, args);
        ) {
            test1(context);
        }
    }

    @Async
    public Future<Object> testAsync() {
        log.info("开始");
        try {
            TimeUnit.SECONDS.sleep(5L);
            log.info("结束");
            return new AsyncResult<>("1111");
        } catch (InterruptedException e) {
            log.info("手动中断");
        }
        log.info("结束");
        return null;
    }


    private static void test1(ConfigurableApplicationContext context) {
        final TestAsync bean = context.getBean(TestAsync.class);
        final Future<Object> rs = bean.testAsync();
        Scanner s = new Scanner(System.in);
        if (Objects.equals("I",s.next())){
            rs.cancel(true);
        }
        try {
            log.info("结果 : {}", rs.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
