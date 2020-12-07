package io.github.jzdayz.jdk.soft;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {

    public static class TestBean {

    }

    public static void main(String[] args) {
        TestBean s = new TestBean();
        WeakReference<TestBean> test = new WeakReference<>(s);
        Executors.newScheduledThreadPool(1)
                .schedule(() -> {
                    System.gc();
                    System.out.println("GC !!!!!!");
                }, 10, TimeUnit.SECONDS);
        Executors.newScheduledThreadPool(1)
                .scheduleWithFixedDelay(() -> System.out.println(test.get()),1, 1, TimeUnit.SECONDS);

    }

}
