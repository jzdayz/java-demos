package io.github.jzdayz.jdk.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class DateTests {
    public static void main(String[] args) throws Exception{
        test1();
    }

    private static void test1() throws Exception{

        Date date = DateUtils.parseDate("2020-01-01", "yyyy-MM-dd");
        Date date1 = DateUtils.parseDate("2020-01-02", "yyyy-MM-dd");

        System.out.println(date.compareTo(date1));


    }
}
