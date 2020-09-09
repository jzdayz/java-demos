package io.github.jzdayz.jdk.concurrent;


import lombok.extern.slf4j.Slf4j;
import org.jooq.meta.derby.sys.Sys;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class AQS {
    public static void main(String[] args) throws InterruptedException {
        main2();
    }

    private static void main2() throws InterruptedException {
        Lock lock = new ReentrantLockWritable(false);
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(()->{
            log.info("pre lock");
            lock.lock();
            log.info("locked");
            try {
                log.info("pre await");
                condition.await();
                log.info("wait ended");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("pre unlock");
            lock.unlock();
        },"test1");
        Thread t2 = new Thread(()->{
            log.info("pre lock");
            lock.lock();
            log.info("locked");
            condition.signal();
            log.info("did signal");
            lock.unlock();
            log.info("end");
        },"test2");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100L);
        t2.start();

    }

    private static void main1() throws InterruptedException {
        Lock lock = new ReentrantLockWritable(false);
        Condition condition = lock.newCondition();


        CountDownLatch countDownLatch1 = new CountDownLatch(1);

        Thread t1 = new Thread(()->{
            lock.lock();
            // 可重入锁，每次获得锁都得release
//            lock.lock();
            countDownLatch1.countDown();
            Scanner s = new Scanner(System.in);
            s.next();
            lock.unlock();
        },"test1");
        Thread t2 = new Thread(()->{
            try {
                countDownLatch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            log.info("GET LOCK");
            lock.unlock();
            log.info("RELEASE LOCK");
        },"test2");

        t2.start();
        Thread.sleep(200L);
        t1.start();

    }
}
