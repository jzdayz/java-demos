package io.github.jzdayz.spring.framework;

import org.springframework.core.ResolvableType;

import java.util.ArrayList;

public class ResolvableTypeTests {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ResolvableType resolvableType = ResolvableType.forClass(B.class);
        System.out.println();
    }

    private static class A<T>{
        private T t;
    }

    private static class B extends ArrayList<A<String>> {

    }
}
