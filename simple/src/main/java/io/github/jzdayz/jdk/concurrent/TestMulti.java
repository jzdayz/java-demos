package io.github.jzdayz.jdk.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMulti {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch cdl =new CountDownLatch(2);

    AtomicInteger i = new AtomicInteger(2);
    Thread t1 = new Thread(() -> {
      int i1 = i.decrementAndGet();
      if (i1== 0){
        System.out.println("DONE");
      }
      cdl.countDown();
    });

    Thread t2 = new Thread(() -> {
      int i1 = i.decrementAndGet();
      if (i1== 0){
        System.out.println("DONE");
      }
      cdl.countDown();
    });


    t1.start();
    t2.start();
    cdl.await();
    System.out.println("END");

  }

}
