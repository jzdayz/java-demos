package io.github.jzdayz.jdk.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        main2();
        main3();
        main4();
        main5();
    }

    private static void main5() {
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7})
                .parallel()
                .forEach(e -> {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                });
    }

    private static void main4() {
        for (int i = 0; i < 1000; i++) {
            ForkJoinTask<Void> task = new ForkJoinTask<Void>() {

                public final Void getRawResult() {
                    return null;
                }

                public final void setRawResult(Void v) {
                }

                public final boolean exec() {
                    run();
                    return true;
                }

                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            if (i % 10 == 0) {
                task.invoke();
            } else {
                ForkJoinPool.commonPool().execute(
                        task
                );
            }

        }
    }

    private static void main3() throws Exception {
        List<CompletableFuture<Void>> rs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            rs.add(CompletableFuture.runAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        CompletableFuture.allOf(rs.toArray(new CompletableFuture[0])).get();
    }

    private static void main2() throws Exception {
        CompletableFuture<Void> a1 = CompletableFuture.runAsync(() -> {
            Unchecked.runnable(() -> TimeUnit.MILLISECONDS.sleep(100L)).run();
            log.info("done!");
        });
        a1.get();
    }

    private static void main1() throws Exception {
        CompletableFuture<Void> a1 = CompletableFuture.runAsync(() -> {
            Unchecked.runnable(() -> TimeUnit.MILLISECONDS.sleep(100L)).run();
            log.info("done!");
        });
        CompletableFuture<Void> a2 = CompletableFuture.runAsync(() -> {
            Unchecked.runnable(() -> TimeUnit.MILLISECONDS.sleep(1000L)).run();
            log.info("done!");
        });
        CompletableFuture<Void> rs = CompletableFuture.allOf(a1, a2);
        log.info("start get");
        rs.get();
        log.info("end");
    }
}
