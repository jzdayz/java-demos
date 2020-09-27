package io.github.jzdayz.jdk.math;

import java.math.BigDecimal;

public class BigDecimalTests {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        BigDecimal bigDecimal = new BigDecimal("1.00");
        BigDecimal bigDecimal1 = new BigDecimal(1);
        System.out.println(bigDecimal.equals(bigDecimal1));
        System.out.println(bigDecimal.compareTo(bigDecimal1));
    }

}
