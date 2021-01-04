package io.github.jzdayz.apache.pool;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws Exception{
        ArrayBlockingQueue a = new ArrayBlockingQueue(1);
        a.put("1");
        a.put("2");
        System.out.println(1);

    }
}
