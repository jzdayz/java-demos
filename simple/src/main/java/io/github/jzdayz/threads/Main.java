package io.github.jzdayz.threads;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws Exception{
    Thread a = new Thread(()->{
      try {
        TimeUnit.DAYS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.err.println(Thread.currentThread().getName()+" done");
    });

    Thread b = new Thread(()->{
      try {
        TimeUnit.DAYS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.err.println(Thread.currentThread().getName()+" done");
    });

    a.setName("a");
    b.setName("b");

    a.start();
    b.start();


    List<Thread> threads = ThreadUtils.getThreads();
    threads.forEach(System.out::println);
    for (Thread thread : threads) {
      // 杀掉名称为a的线程
      if (Objects.equals("a",thread.getName())){
        // 不推荐 stop,最好是中断,如果需要强制停掉,stop也尚可
//        thread.stop();
        thread.interrupt();
        System.out.println("kill thread for name 'a'");
      }
    }

    System.out.println("exec done");
    System.exit(1);
  }

}
