package io.github.jzdayz.apache.pool;

import java.util.concurrent.TimeUnit;

public class Test {
    public static int[] ints = new int[5];
    public static void main(String[] args) throws Exception {
        Object o = new Object();
        new Thread(() -> {
            //线程A
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ints[0] = 2;
        }).start();
        new Thread(() -> {            //线程B
            while (true) {
                try {
                    Thread.sleep(0L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ints[0] == 2) {
                    System.out.println("结束");
                    break;
                }
            }
        }).start();
    }
}
