package io.github.jzdayz.jdk.threads;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestSchedule {

  public static void main(String[] args) {
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleAtFixedRate(()-> System.out.println(1),2,2, TimeUnit.SECONDS);
  }

}
