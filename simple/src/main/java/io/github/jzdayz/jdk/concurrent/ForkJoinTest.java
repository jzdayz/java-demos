package io.github.jzdayz.jdk.concurrent;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinTest {

    static {
        // 设置一下公用的线程池的大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
    }


    public static void main(String[] args) {
//        ForkJoinPool.commonPool().execute(() -> {
//            try {
//                TimeUnit.DAYS.sleep(1L);
//            } catch (InterruptedException e) {
//                log.error(e.getLocalizedMessage(), e);
//            }
//        });
        new PageTask(1, 100).compute();
    }

    @AllArgsConstructor
    @ToString
    public static class PageTask extends RecursiveTask<Integer> {
        private int start;
        private int end;
        final static int THRESHOLD = 1;


        public Integer compute() {
            if ((end - start) <= THRESHOLD) {
                log.info("数据页码：{}", end);
                return null;
            } else {
                /**
                 *  forkJoin思想
                 *  将任务分尝试分发出去，如果分发失败，则在当前线程进行处理
                 */
                int middle = (start + end) / 2;
                PageTask left = new PageTask(start, middle);
                PageTask right = new PageTask(middle, end);
                // 将任务尝试分发出去
                right.fork();
                // 如果这个任务，没有别的线程领走的话，就在当前线程进行处理
                right.join();
                left.compute();
                return null;
            }

        }
    }


    public static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        public Integer compute() {
            if (n <= 1)
                return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return f2.compute() + f1.join();
        }
    }

}
