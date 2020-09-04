package io.github.jzdayz.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo {

    @SuppressWarnings("UnstableApiUsage")
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(
                1, 1, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100),
                (r) -> {
                    Thread t = new Thread(r);
                    t.setName("哈哈家具");
                    t.setDaemon(true);
                    return t;
                }
        );
        AsyncEventBus eventBus = new AsyncEventBus(executorService);

        eventBus.register(new E());

        eventBus.post(new StartedEvent());

        System.out.println("done");

        TimeUnit.SECONDS.sleep(2L);

    }

    public static class StartedEvent {

        @Override
        public String toString() {
            return "StartedEvent{}";
        }
    }

    public static class E {

        @Subscribe
        public void listener(StartedEvent event) {
            System.out.println(Thread.currentThread());
            System.out.println(event);
        }

    }
}
