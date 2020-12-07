package io.github.jzdayz.hutool;

import cn.hutool.core.convert.Convert;

import java.math.BigDecimal;

public class Demo1 {
    public static void main(String[] args) {
        final BigDecimal bigDecimal = Convert.toBigDecimal("1111");
        System.out.println(bigDecimal);
    }
}
