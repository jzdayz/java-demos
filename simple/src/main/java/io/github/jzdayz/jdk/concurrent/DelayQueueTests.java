package io.github.jzdayz.jdk.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DelayQueueTests {
    public static void main(String[] args) throws Exception {

        DelayQueue<DelayQueueE> queue = new DelayQueue<>();
        queue.offer(new DelayQueueE());
        queue.offer(new DelayQueueE());
        queue.offer(new DelayQueueE());
        queue.offer(new DelayQueueE());
        int size = queue.size();
        AtomicInteger consumed = new AtomicInteger(0);

        ThreadPoolExecutor t = new ThreadPoolExecutor(5, 5, 5L, TimeUnit.HOURS, new ArrayBlockingQueue<>(100));
        for (int i = 0; i < 3; i++) {
            t.submit(() -> {
                for (; ; ) {
                    try {
                        DelayQueueE e = queue.take();
                        e.run();
                        consumed.incrementAndGet();
                    } catch (InterruptedException e) {
                        // 不可以忽略中断异常
                        throw e;
                    } catch (Exception ignore) {

                    }
                }
            });
        }
        for (; ; ) {
            TimeUnit.SECONDS.sleep(1L);
            if (consumed.get() == size) {
                break;
            }
        }
        t.shutdownNow();
        System.out.println("done");
    }

    static class DelayQueueE implements Delayed, Runnable {

        private long last;

        private long expired;

        /**
         * 这个方法是线程安全的，在延迟队列中，会被锁锁住
         */
        @Override
        public long getDelay(TimeUnit unit) {
            last = System.currentTimeMillis();
            if (expired == 0) {
                expired = last + TimeUnit.SECONDS.toMillis(1L);
            }
            // 延迟1s
            return unit.convert(expired - last, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "-GET");
        }
    }
}
