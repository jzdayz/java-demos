package io.github.jzdayz.jdk.concurrent;

public class ThreadTests {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {

        Thread.currentThread().interrupt();

        System.out.println(Thread.currentThread().isInterrupted());

        System.out.println("done");

    }
}
