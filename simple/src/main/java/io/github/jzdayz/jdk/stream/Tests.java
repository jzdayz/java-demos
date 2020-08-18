package io.github.jzdayz.jdk.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Tests {
    public static void main(String[] args) throws Exception{
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        // 这里其实相当于使用了自定义的forkJoinPool，主要是缘于forkJoinTask.fork()会尝试匹配当前执行线程
        // https://stackoverflow.com/questions/21163108/custom-thread-pool-in-java-8-parallel-stream/22269778#22269778
        // 需要注意，这么使用需要在较高的jdk8上运行 https://bugs.openjdk.java.net/browse/JDK-8224620
        forkJoinPool.submit(() -> s.parallelStream().forEach((number) -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread()+"--"+number);
        }));
        TimeUnit.HOURS.sleep(1L);
    }
}
