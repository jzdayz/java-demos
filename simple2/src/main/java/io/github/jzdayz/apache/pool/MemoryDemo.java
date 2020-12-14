package io.github.jzdayz.apache.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MemoryDemo {

    public static class Holder{
        volatile Bean bean = new Bean();
    }

    public static void main(String[] args) throws InterruptedException {
        Holder holder = new Holder();
        Bean p = new Bean();
        Bean q = p;

        CountDownLatch cdl = new CountDownLatch(1);

        Runnable a = () -> {
            Bean r1 = q;
            int r2 = r1.x;
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bean r3 = q;
            int r4 = r3.x;
            int r5 = r1.x;
            System.out.printf("r2:%s,r4:%s,r5:%s", r2, r4, r5);
        };
        Runnable b = () -> {
            Bean r6 = q;
            r6.x = 3;
            cdl.countDown();
        };

        new Thread(a).start();
        new Thread(b).start();
        TimeUnit.DAYS.sleep(1L);
    }

    public static class Bean {
        private int x = 0;
    }

}
