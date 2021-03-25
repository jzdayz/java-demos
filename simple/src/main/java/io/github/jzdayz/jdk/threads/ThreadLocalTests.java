package io.github.jzdayz.jdk.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalTests {

  private static final InheritableThreadLocal<String> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

  public static void main(String[] args) throws Exception {
    INHERITABLE_THREAD_LOCAL.set("hah");
    log.info(INHERITABLE_THREAD_LOCAL.get());
    new Thread(() -> log.info(INHERITABLE_THREAD_LOCAL.get())).start();
    Thread.sleep(1000L);
    log.info("DONE!");
  }


}
