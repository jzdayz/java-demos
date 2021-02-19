package io.github.jzdayz.jdk.threads;

import java.util.concurrent.locks.LockSupport;

public class T2 {


  public static void main(String[] args) throws Exception{
    Thread t = new Thread(()->{
      System.out.println("start lock");
      try {
        LockSupport.park();
      }catch (Exception e){
        e.printStackTrace();
      }
      if (Thread.interrupted()){
        System.out.println("中断了！！！！！！！");
      }
      System.out.println("end ");

    });

    t.start();
    Thread.sleep(0L);
    t.interrupt();

    System.out.println("1");

  }

}
