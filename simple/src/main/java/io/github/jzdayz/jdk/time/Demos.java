package io.github.jzdayz.jdk.time;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Demos {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        int i = LocalDateTime.now().get(ChronoField.MINUTE_OF_HOUR);
        System.out.println(i);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = ChronoUnit.DAYS.addTo(now, 10);
        System.out.println(localDateTime);
    }
}
