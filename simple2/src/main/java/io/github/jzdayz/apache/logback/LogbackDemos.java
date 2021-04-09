package io.github.jzdayz.apache.logback;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class LogbackDemos {
    public static void main(String[] args) throws Exception {
        String[] msg = new String[]{"1", "2", "3", "4"};
        try {
            a();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        TimeUnit.DAYS.sleep(1L);
    }

    public static void a() {
        b();
    }

    public static void b() {
        throw new RuntimeException("错误！！！");
    }
}
