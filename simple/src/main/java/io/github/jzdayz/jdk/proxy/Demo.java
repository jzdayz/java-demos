package io.github.jzdayz.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo {

    interface A{
        void a();
    }

    interface B{
        void b();
    }

    static class C implements A,B{

        @Override
        public void a() {
            System.out.println("a");
        }

        @Override
        public void b() {
            System.out.println("b");
        }
    }

    public static void main(String[] args) {
    }

}
