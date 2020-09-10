package io.github.jzdayz.jdk.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        main2();
    }

    private static void main2() throws Exception{
        CompletableFuture<Void> a1 = CompletableFuture.runAsync(() -> {
            Unchecked.runnable(()-> TimeUnit.MILLISECONDS.sleep(100L)).run();
            log.info("done!");
        });
        a1.get();
    }

    private static void main1() throws Exception {
        CompletableFuture<Void> a1 = CompletableFuture.runAsync(() -> {
            Unchecked.runnable(()-> TimeUnit.MILLISECONDS.sleep(100L)).run();
            log.info("done!");
        });
        CompletableFuture<Void> a2 = CompletableFuture.runAsync(() -> {
            Unchecked.runnable(()-> TimeUnit.MILLISECONDS.sleep(1000L)).run();
            log.info("done!");
        });
        CompletableFuture<Void> rs = CompletableFuture.allOf(a1, a2);
        log.info("start get");
        rs.get();
        log.info("end");
    }
}
