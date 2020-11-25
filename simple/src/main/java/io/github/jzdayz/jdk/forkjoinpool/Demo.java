package io.github.jzdayz.jdk.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ForkJoinPool.defaultForkJoinWorkerThreadFactory;

public class Demo {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(64);
        for (int i = 0; i < 100; i++) {
            forkJoinPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        try {
            TimeUnit.DAYS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
