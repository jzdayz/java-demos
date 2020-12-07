package io.github.jzdayz.boot.event;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class A {
    private final static List<String> test = new ArrayList<>();

    interface B<T>{
        T test();
    }

    public static class BB implements B<String>{

        @Override
        public String test() {
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        ResolvableType.forField(A.class.getDeclaredField("test")).getGeneric(0);
    }
}
