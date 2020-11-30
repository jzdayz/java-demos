package io.github.jzdayz.jdk.forkjoinpool;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        new Thread(scanner::next,"cc").start();
        try {
            TimeUnit.DAYS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(20);
        final Scanner scanner = new Scanner(System.in);
        scanner.next();
        for (int i = 0; i < 10000; i++) {
            forkJoinPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            TimeUnit.DAYS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
