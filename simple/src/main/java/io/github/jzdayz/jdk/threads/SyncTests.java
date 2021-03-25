package io.github.jzdayz.jdk.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncTests {

  private static Object lock = new Object();

  public static void main(String[] args) throws Exception{
    Test test = new Test();
//    new Thread(()->{
//      try {
//        Thread.sleep(1000);
//        test.t1();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    },"await thread").start();
    new Thread(()->{
      try {
        Thread.sleep(1000);
        test.t2();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    },"notify thread").start();
    test.t1();
  }

  private static class Test{

    public void t1() throws InterruptedException {
      synchronized (lock){
        log.info("before wait");
        lock.wait();
        log.info("wakeup 1");
        lock.wait();
        log.info("after wait");
      }
    }

    public void t2() throws InterruptedException{
      synchronized (lock){
        log.info("before notify");
        lock.notify();
        Thread.sleep(1000);
        log.info("after notify");
      }
    }

  }

}
