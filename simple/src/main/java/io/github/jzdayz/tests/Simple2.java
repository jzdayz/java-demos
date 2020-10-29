package io.github.jzdayz.tests;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Simple2 {

    private static final ThreadPoolExecutor E = new ThreadPoolExecutor(1,1,1L, TimeUnit.HOURS,new ArrayBlockingQueue<>(100));

    public static void main(String[] args) {
        E.submit(()-> System.out.println(1111));
    }

    public void show(){
        System.out.println("show");
    }
}
